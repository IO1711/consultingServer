package com.bilolbek.ConsultingWebsite.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bilolbek.ConsultingWebsite.DTO.CourseDTO;
import com.bilolbek.ConsultingWebsite.DTO.DocCheckDTO;
import com.bilolbek.ConsultingWebsite.DTO.LearnerDTO;
import com.bilolbek.ConsultingWebsite.DTO.UserDetailsDTO;
import com.bilolbek.ConsultingWebsite.models.AppUser;
import com.bilolbek.ConsultingWebsite.models.Course;
import com.bilolbek.ConsultingWebsite.models.DocCheck;
import com.bilolbek.ConsultingWebsite.models.DocCheckFiles;
import com.bilolbek.ConsultingWebsite.models.DocCheckNotes;
import com.bilolbek.ConsultingWebsite.models.Learners;
import com.bilolbek.ConsultingWebsite.models.Opportunity;
import com.bilolbek.ConsultingWebsite.models.Recordings;
import com.bilolbek.ConsultingWebsite.models.Resources;
import com.bilolbek.ConsultingWebsite.models.TestModel;
import com.bilolbek.ConsultingWebsite.models.VisaRequest;
import com.bilolbek.ConsultingWebsite.repositories.AppUserRepository;
import com.bilolbek.ConsultingWebsite.repositories.CourseRepository;
import com.bilolbek.ConsultingWebsite.repositories.DocCheckFilesRepo;
import com.bilolbek.ConsultingWebsite.repositories.DocCheckNotesRepo;
import com.bilolbek.ConsultingWebsite.repositories.DocCheckRepo;
import com.bilolbek.ConsultingWebsite.repositories.LearnersRepository;
import com.bilolbek.ConsultingWebsite.repositories.OpportunityRepo;
import com.bilolbek.ConsultingWebsite.repositories.RecordingsRepo;
import com.bilolbek.ConsultingWebsite.repositories.ResourcesRepo;
import com.bilolbek.ConsultingWebsite.repositories.TestModelRepository;
import com.bilolbek.ConsultingWebsite.repositories.VisaRequestRepo;

import jakarta.persistence.EntityNotFoundException;



@Service
public class GetDataService {

    @Autowired
    private OpportunityRepo opportunityRepo;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TestModelRepository testModelRepository;

    @Autowired
    private RecordingsRepo recordingsRepo;

    @Autowired
    private LearnersRepository learnersRepository;

    @Autowired
    private ResourcesRepo resourcesRepo;

    @Autowired
    private DocCheckRepo docCheckRepo;

    @Autowired
    private DocCheckFilesRepo docCheckFilesRepo;

    @Autowired
    private DocCheckNotesRepo docCheckNotesRepo;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private VisaRequestRepo visaRequestRepo;


    @Autowired
    private UserService userService;
    
    
    public ResponseEntity<?> getAllOpportunities() {
        try {
            List<Opportunity> allResults = opportunityRepo.findAll();
            return ResponseEntity.ok(allResults);
        } catch (DataAccessException e) {
            // log.error("DB error fetching opportunities", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Database error while fetching opportunities"));
        } catch (Exception e) {
            // log.error("Unexpected error fetching opportunities", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Unexpected error while fetching opportunities"));
        }
    }

    public ResponseEntity<?> getCourse(long courseId){
        try {
            Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found"));
            return ResponseEntity.ok(course);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Database error while fetching courses"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Unexpected error while fetching courses"));
        }
    }

    public ResponseEntity<?> getAllCourses() {
        try {
            List<Course> allCourses = courseRepository.findAll();
            List<CourseDTO> resultsToReturn = new ArrayList<>();

            for(Course course : allCourses){
                List<Learners> courseLearners = learnersRepository.findByCourse(course);
                CourseDTO c = new CourseDTO(course.getId(), course.getTitle(), course.getStartDate(), course.getEndDate(), course.getDescription(), course.getAbout(), course.getLengthInWeek(), course.getCurrentProgress(), courseLearners.size());
                resultsToReturn.add(c);
            }

            return ResponseEntity.ok(resultsToReturn);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Database error while fetching courses"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Unexpected error while fetching courses"));
        }
    }

    public List<Recordings> getRecordings(long courseId){
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found in getRecordings"));
        
        List<Recordings> recordings = recordingsRepo.findByCourse(course);

        return recordings;
    }

    public ResponseEntity<?> getResources(long courseId){

        try {
            Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found"));

            List<Resources> courseResources = resourcesRepo.findByCourse(course);

            return ResponseEntity.ok(courseResources);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Database error while fetching resources for the course with id " + courseId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Unexpected error while fetching resources for the course with id " + courseId));
        }
    }

    public List<LearnerDTO> getLearners(long courseId){
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new EntityNotFoundException("Course not found with id " + courseId));

        List<Learners> courseLearners = learnersRepository.findByCourse(course);

        List<LearnerDTO> resultToReturn = new ArrayList<>();

        for(Learners learner : courseLearners){
            LearnerDTO learnerInfo = new LearnerDTO(learner.getId(), learner.getStudent().getEmail(), courseId);

            resultToReturn.add(learnerInfo);
        }

        return resultToReturn;
    }

    public List<DocCheckDTO> getDocRequests(){

        List<DocCheck> allRequests = docCheckRepo.findAll();

        List<DocCheckDTO> returnResult = new ArrayList<>();

        for(DocCheck request : allRequests){
            List<DocCheckFiles> requestFiles = docCheckFilesRepo.findByDocCheck(request);
            DocCheckDTO result = new DocCheckDTO(request.getId(), request.getProgram(), request.getSubmittedAt(), request.getStatus(), request.getCommennt(), requestFiles.size(), new ArrayList<>());
            result.setFirstname(request.getUser().getFirstname());
            result.setLastname(request.getUser().getLastname());
            returnResult.add(result);
        }

        return returnResult;

    }

    public DocCheckDTO getDocRequest(long requestId){
        DocCheck docCheck = docCheckRepo.findById(requestId)
            .orElseThrow(() -> new EntityNotFoundException("DocCheck request with id " + requestId + " not found"));
        
        List<DocCheckFiles> requestFiles = docCheckFilesRepo.findByDocCheck(docCheck);
        DocCheckDTO request = new DocCheckDTO(docCheck.getId(), docCheck.getProgram(), docCheck.getSubmittedAt(), docCheck.getStatus(), docCheck.getCommennt(), requestFiles.size(), requestFiles);

        request.setFirstname(docCheck.getUser().getFirstname());
        request.setLastname(docCheck.getUser().getLastname());

        return request;
    }

    public List<DocCheckFiles> getRequestFiles(long requestId){
        DocCheck request = docCheckRepo.findById(requestId)
            .orElseThrow(() -> new EntityNotFoundException("DocCheck request with id " + requestId + " not found"));
        
        List<DocCheckFiles> requestFiles = docCheckFilesRepo.findByDocCheck(request);

        return requestFiles;
    }

    public List<DocCheckNotes> getDocCheckNotes(long requestId){
        DocCheck request = docCheckRepo.findById(requestId)
            .orElseThrow(() -> new EntityNotFoundException("DocCheck request with id " + requestId + " not found"));

        List<DocCheckNotes> notes = docCheckNotesRepo.findByDocCheck(request);

        return notes;
    }

    public List<Course> getUserCourses(){
        UserDetailsDTO userDetailsDTO = userService.getUserDetails();

        AppUser user = appUserRepository.findByEmail(userDetailsDTO.getEmail());

        List<Learners> learnerData = learnersRepository.findByStudent(user);

        List<Course> learnerCourses = new ArrayList<>();

        for(Learners learner : learnerData){
            learnerCourses.add(learner.getCourse());
        }

        return learnerCourses;
    }

    public List<DocCheck> getUserDocRequests(){
        UserDetailsDTO userDTO = userService.getUserDetails();

        AppUser user = appUserRepository.findByEmail(userDTO.getEmail());

        List<DocCheck> userRequests = docCheckRepo.findByUser(user);

        return userRequests;
    }

    public List<VisaRequest> getUserVisaRequests(){
        UserDetailsDTO userDTO = userService.getUserDetails();

        AppUser user = appUserRepository.findByEmail(userDTO.getEmail());

        List<VisaRequest> userRequests = visaRequestRepo.findByUser(user);

        return userRequests;
    }

    
    public List<TestModel> getTestData(){
        TestModel test = new TestModel("I am test model");

        testModelRepository.save(test);

        TestModel test2 = new TestModel("I am also a test model");

        testModelRepository.save(test2);

        List<TestModel> allTests = testModelRepository.findAll();

        return allTests;
    }
}
