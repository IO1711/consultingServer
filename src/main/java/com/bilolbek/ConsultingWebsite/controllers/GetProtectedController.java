package com.bilolbek.ConsultingWebsite.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bilolbek.ConsultingWebsite.DTO.DocCheckDTO;
import com.bilolbek.ConsultingWebsite.DTO.LearnerDTO;
import com.bilolbek.ConsultingWebsite.DTO.UserDetailsDTO;
import com.bilolbek.ConsultingWebsite.models.Course;
import com.bilolbek.ConsultingWebsite.models.DocCheck;
import com.bilolbek.ConsultingWebsite.models.DocCheckNotes;
import com.bilolbek.ConsultingWebsite.models.Recordings;
import com.bilolbek.ConsultingWebsite.models.VisaRequest;
import com.bilolbek.ConsultingWebsite.services.FileService;
import com.bilolbek.ConsultingWebsite.services.GetDataService;
import com.bilolbek.ConsultingWebsite.services.UserService;

@RestController
@RequestMapping("/api/v1/getProtected/")
public class GetProtectedController {

    @Autowired
    private GetDataService getDataService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @GetMapping("getCurrentUser")
    public UserDetailsDTO getCurrentUser(){
        return userService.getUserDetails();
    }

    @GetMapping("getUserCourses")
    public List<Course> getUserCourses(){
        return getDataService.getUserCourses();
    }

    @GetMapping("getUserDocRequests")
    public List<DocCheck> getUserDocRequests(){
        return getDataService.getUserDocRequests();
    }

    @GetMapping("getUserVisaRequests")
    public List<VisaRequest> getUserVisaRequests(){
        return getDataService.getUserVisaRequests();
    }

    @GetMapping("verifyStudent/{courseId}")
    public ResponseEntity<Map<String, Boolean>> verifyStudent(@PathVariable long courseId){
        return getDataService.verifyStudent(courseId);
    }

    @GetMapping("getRecordings/{courseId}")
    public List<Recordings> getRecordings(@PathVariable long courseId){
        return getDataService.getRecordings(courseId);
    }

    @GetMapping("getResources/{courseId}")
    public ResponseEntity<?> getResources(@PathVariable long courseId){
        return getDataService.getResources(courseId);
    }

    @GetMapping("downloadResource/{filename}")
    public ResponseEntity<Resource> downloadResource(@PathVariable String filename){
        return fileService.getFile(filename);
    }

    @GetMapping("getLearners/{courseId}")
    public List<LearnerDTO> getLearners(@PathVariable long courseId){
        return getDataService.getLearners(courseId);
    }

    @GetMapping("getDocRequest/{requestId}")
    public DocCheckDTO getDocRequest(@PathVariable long requestId){
        return getDataService.getDocRequest(requestId);
    }

    @GetMapping("getDocCheckNotes/{requestId}")
    public List<DocCheckNotes> getDocCheckNotes(@PathVariable("requestId") long requestId){
        return getDataService.getDocCheckNotes(requestId);
    }

}
