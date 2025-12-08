package com.bilolbek.ConsultingWebsite.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bilolbek.ConsultingWebsite.DTO.DocCheckNotesDTO;
import com.bilolbek.ConsultingWebsite.DTO.JoinCourseDTO;
import com.bilolbek.ConsultingWebsite.DTO.LearnerDTO;
import com.bilolbek.ConsultingWebsite.DTO.RecordingsDTO;
import com.bilolbek.ConsultingWebsite.DTO.UserDetailsDTO;
import com.bilolbek.ConsultingWebsite.DTO.VisaRequestDTO;
import com.bilolbek.ConsultingWebsite.components.TelegramBot;
import com.bilolbek.ConsultingWebsite.models.AppUser;
import com.bilolbek.ConsultingWebsite.models.Course;
import com.bilolbek.ConsultingWebsite.models.DocCheck;
import com.bilolbek.ConsultingWebsite.models.DocCheckFiles;
import com.bilolbek.ConsultingWebsite.models.DocCheckNotes;
import com.bilolbek.ConsultingWebsite.models.JoinCourseRequest;
import com.bilolbek.ConsultingWebsite.models.Learners;
import com.bilolbek.ConsultingWebsite.models.Opportunity;
import com.bilolbek.ConsultingWebsite.models.Recordings;
import com.bilolbek.ConsultingWebsite.models.VisaRequest;
import com.bilolbek.ConsultingWebsite.repositories.AppUserRepository;
import com.bilolbek.ConsultingWebsite.repositories.CourseRepository;
import com.bilolbek.ConsultingWebsite.repositories.DocCheckFilesRepo;
import com.bilolbek.ConsultingWebsite.repositories.DocCheckNotesRepo;
import com.bilolbek.ConsultingWebsite.repositories.DocCheckRepo;
import com.bilolbek.ConsultingWebsite.repositories.JoinCourseRequestRepo;
import com.bilolbek.ConsultingWebsite.repositories.LearnersRepository;
import com.bilolbek.ConsultingWebsite.repositories.OpportunityRepo;
import com.bilolbek.ConsultingWebsite.repositories.RecordingsRepo;
import com.bilolbek.ConsultingWebsite.repositories.VisaRequestRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class SaveDataService {

    @Value("${telegramChatId}")
    private long chatId;

    @Autowired
    private OpportunityRepo opportunityRepo;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RecordingsRepo recordingsRepo;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private LearnersRepository learnersRepository;

    @Autowired
    private DocCheckRepo docCheckRepo;

    @Autowired
    private DocCheckFilesRepo docCheckFilesRepo;

    @Autowired
    private DocCheckNotesRepo docCheckNotesRepo;

    @Autowired
    private VisaRequestRepo visaRequestRepo;

    @Autowired
    private JoinCourseRequestRepo joinCourseRequestRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;


    @Autowired
    private TelegramBot telegramBot;

    @Transactional
    public ResponseEntity<Map<String, String>> saveOpportunity(Opportunity opportunity) {
        try {
            opportunityRepo.save(opportunity);
            return ResponseEntity.ok(Map.of("message", "Opportunity saved successfully"));
        } catch (DataAccessException e) {
            // Catches Spring database exceptions (constraint, connection, etc.)
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Database error while saving opportunity"));
        } catch (Exception e) {
        // Catches any unexpected error
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Unexpected error: " + e.getMessage()));
        }
    }

    @Transactional
    public ResponseEntity<Map<String, String>> saveCourse(Course course){
        try {
            Course newCourse = new Course(course.getTitle(), course.getStartDate(), course.getEndDate(), course.getDescription(), course.getAbout(), course.getLanguage(), course.getPrice());
            courseRepository.save(newCourse);
            return ResponseEntity.ok(Map.of("message", "Course saved successfully"));
        } catch (DataAccessException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Database error while saving course"));
        } catch (Exception e) {
            
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error: " + e.getMessage()));
        }
    }

    @Transactional
    public ResponseEntity<Map<String, String>> saveRecordings(List<RecordingsDTO> recordingsDTOs){
        Course course = courseRepository.findById(recordingsDTOs.get(0).getCourseId())
            .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + recordingsDTOs.get(0).getCourseId() + "in saveRecordinga"));

        for(RecordingsDTO recordingsDTO : recordingsDTOs) {
            Recordings newRecording = new Recordings(recordingsDTO.getCode(), course);

            recordingsRepo.save(newRecording);
        }

        return ResponseEntity.ok(Map.of("message", "Recordings are saved"));
    }


    @Transactional
    public ResponseEntity<Map<String, String>> saveLearner(List<LearnerDTO> newLearners){
        for(LearnerDTO newLearner : newLearners){
            AppUser user = appUserRepository.findById(newLearner.getId())
                .orElseThrow(() -> new EntityNotFoundException("Could not load user with id " + newLearner.getId()));
            
            Course course = courseRepository.findById(newLearner.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id " + newLearner.getCourseId()));
            
            Optional<Learners> existting = learnersRepository.findByStudentAndCourse(user, course);

            if(existting.isEmpty()){
                Learners learner = new Learners();

                learner.setStudent(user);
                learner.setCourse(course);
                learnersRepository.save(learner);
            }

            JoinCourseRequest request = joinCourseRequestRepo.findByCourseAndUser(course, user);

            if(request != null){
                joinCourseRequestRepo.delete(request);
            }


            
        }

        return ResponseEntity.ok(Map.of("message", "Learners added successfully"));
    }


    @Transactional
    public ResponseEntity<Map<String, String>> saveDocCheckRequest(List<MultipartFile> files, String program, String comment)throws IOException{
        UserDetailsDTO userDetails = userService.getUserDetails();
        AppUser user = appUserRepository.findByEmail(userDetails.getEmail());

        DocCheck newRequest = new DocCheck(user, program, comment);
        docCheckRepo.save(newRequest);

        for(MultipartFile file : files){
            String savedFilename = fileService.saveFile(file);

            DocCheckFiles requestFile = new DocCheckFiles(newRequest, savedFilename);

            docCheckFilesRepo.save(requestFile);
        }


        return ResponseEntity.ok(Map.of("message", "Documents saved successfully"));
    }

    @Transactional
    public ResponseEntity<Map<String,String>> saveDocCheckNotes(List<DocCheckNotesDTO> notesDTOs){
        DocCheck request = docCheckRepo.findById(notesDTOs.get(0).getDocCheckId())
            .orElseThrow(() -> new EntityNotFoundException("DocCheck request with id " + notesDTOs.get(0).getDocCheckId() + " not found"));
        
        for(DocCheckNotesDTO notesDTO : notesDTOs){
            DocCheckNotes note = new DocCheckNotes(notesDTO.getTitle(), notesDTO.getNote(), request);

            docCheckNotesRepo.save(note);
        }

        return ResponseEntity.ok(Map.of("message","Notes added successfully"));
    }

    @Transactional
    public ResponseEntity<Map<String, String>> saveVisaRequest(VisaRequestDTO visaRequestDTO){
        UserDetailsDTO userDTO = userService.getUserDetails();

        AppUser user = appUserRepository.findByEmail(userDTO.getEmail());

        VisaRequest request = new VisaRequest(user, visaRequestDTO.getTelegram(), visaRequestDTO.getWhatsApp(), visaRequestDTO.getEmail(), visaRequestDTO.getCountry(), visaRequestDTO.getPurpose());

        visaRequestRepo.save(request);

        return ResponseEntity.ok(Map.of("message", "Visa request saved successfully"));
    }

    @Transactional
    public ResponseEntity<Map<String, String>> saveJoinCourseRequest(JoinCourseDTO requestDTO){
        UserDetailsDTO userDTO = userService.getUserDetails();

        AppUser user = appUserRepository.findByEmail(userDTO.getEmail());

        Course course = courseRepository.findById(requestDTO.getCourseId())
            .orElseThrow(() -> new EntityNotFoundException("Course with id " + requestDTO.getCourseId() + " not found"));

        JoinCourseRequest request = new  JoinCourseRequest(course, user, requestDTO.getSchool(), requestDTO.getDegree(), requestDTO.getMajor(), requestDTO.getYear());

        joinCourseRequestRepo.save(request);

        telegramBot.sendMessage(chatId, user.getFirstname() + " " + user.getLastname() + " requests to join the course " + course.getTitle());

        return ResponseEntity.ok(Map.of("message", "Join request saved"));
    }
}
