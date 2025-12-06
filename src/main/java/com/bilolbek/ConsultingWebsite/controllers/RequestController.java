package com.bilolbek.ConsultingWebsite.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bilolbek.ConsultingWebsite.DTO.JoinCourseDTO;
import com.bilolbek.ConsultingWebsite.DTO.VisaRequestDTO;
import com.bilolbek.ConsultingWebsite.services.SaveDataService;

@RestController
@RequestMapping("/api/v1/request/")
public class RequestController {

    @Autowired
    private SaveDataService saveDataService;


    /*
        {
            //formdata
            "files" : "multipartfile array",
            "program" : "string",
            "comment" : "string"
        }
     */
    @PostMapping("docRequest")
    public ResponseEntity<Map<String, String>> docRequest(
        @RequestParam("files") List<MultipartFile> files, 
        @RequestParam("program") String program,
        @RequestParam("comment") String comment) throws IOException{

            return saveDataService.saveDocCheckRequest(files, program, comment);
    }

    /*
        {
            "telegram" : "string",
            "whatsApp" : "string",
            "email" : "string",
            "country" : "string",
            "purpose" : "string"
        }
    */
    @PostMapping("visaRequest")
    public ResponseEntity<Map<String, String>> visaRequest(@RequestBody VisaRequestDTO visaRequestDTO){
        return saveDataService.saveVisaRequest(visaRequestDTO);
    }

    /*
        {
            "courseId" : "long",
            "school" : "string",
            "degree" : "string",
            "major" : "string",
            "year" : "int"
        }
    */
    @PostMapping("joinCourse")
    public ResponseEntity<Map<String, String>> saveJoinCourse(@RequestBody JoinCourseDTO requestDTO){
        return saveDataService.saveJoinCourseRequest(requestDTO);
    }
}
