package com.bilolbek.ConsultingWebsite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.bilolbek.ConsultingWebsite.models.TestModel;
import com.bilolbek.ConsultingWebsite.services.GetDataService;

@RestController
@RequestMapping("/api/v1/get/")
public class GetController {

    @Autowired
    private GetDataService getDataService;

    @GetMapping("")
    public List<TestModel> getData(){
        return getDataService.getTestData();
    }

    @GetMapping("allOpportunities")
    public ResponseEntity<?> getAllOpportunities(){
        return getDataService.getAllOpportunities();
    }

    @GetMapping("allCourses")
    public ResponseEntity<?> getAllCourses(){
        return getDataService.getAllCourses();
    }

}
