package com.bilolbek.ConsultingWebsite.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bilolbek.ConsultingWebsite.models.Course;
import com.bilolbek.ConsultingWebsite.models.DocCheck;
import com.bilolbek.ConsultingWebsite.models.DocCheckFiles;
import com.bilolbek.ConsultingWebsite.models.Recordings;
import com.bilolbek.ConsultingWebsite.models.Resources;
import com.bilolbek.ConsultingWebsite.repositories.CourseRepository;
import com.bilolbek.ConsultingWebsite.repositories.DocCheckFilesRepo;
import com.bilolbek.ConsultingWebsite.repositories.DocCheckRepo;
import com.bilolbek.ConsultingWebsite.repositories.RecordingsRepo;
import com.bilolbek.ConsultingWebsite.repositories.ResourcesRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DeleteDataService {

    @Autowired
    private DocCheckRepo docCheckRepo;
    @Autowired
    private DocCheckFilesRepo docCheckFilesRepo;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private RecordingsRepo recordingsRepo;
    @Autowired
    private ResourcesRepo resourcesRepo;

    @Autowired
    private FileService fileService;

    public ResponseEntity<Map<String, String>> deleteCourse(long courseId){
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found"));

        List<Recordings> courseRecordings = recordingsRepo.findByCourse(course);
        List<Resources> courseResources = resourcesRepo.findByCourse(course);

        for(Recordings recording : courseRecordings){
            recordingsRepo.delete(recording);
        }

        for(Resources resource : courseResources){
            if(fileService.deleteFile(resource.getFilename())){
                resourcesRepo.delete(resource);
            } else {
                return ResponseEntity.ok(Map.of("error","Could not delete file: " + resource.getFilename()));
            }   
        }

        courseRepository.delete(course);

        return ResponseEntity.ok(Map.of("message", "Course with id " + courseId + " has been deleted"));
    }

    public ResponseEntity<Map<String, String>> deleteDocRequest(long id){
        DocCheck request = docCheckRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Document check request with id " + id + " not found"));

        List<DocCheckFiles> allFiles = docCheckFilesRepo.findByDocCheck(request);

        for(DocCheckFiles file : allFiles){
            if(fileService.deleteFile(file.getFilename())){
                docCheckFilesRepo.delete(file);
            } else {
                return ResponseEntity.ok(Map.of("error","Could not delete file: " + file.getFilename()));
            }
        }

        docCheckRepo.delete(request);

        return ResponseEntity.ok(Map.of("message", "Request and it's files are deleted"));
    }
}
