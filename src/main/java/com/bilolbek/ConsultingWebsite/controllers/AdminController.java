package com.bilolbek.ConsultingWebsite.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bilolbek.ConsultingWebsite.DTO.DeleteRequestDTO;
import com.bilolbek.ConsultingWebsite.DTO.DocCheckDTO;
import com.bilolbek.ConsultingWebsite.DTO.DocCheckNotesDTO;
import com.bilolbek.ConsultingWebsite.DTO.DocCheckStatusDTO;
import com.bilolbek.ConsultingWebsite.DTO.LearnerDTO;
import com.bilolbek.ConsultingWebsite.DTO.RecordingsDTO;
import com.bilolbek.ConsultingWebsite.models.Course;
import com.bilolbek.ConsultingWebsite.models.DocCheckFiles;
import com.bilolbek.ConsultingWebsite.models.Opportunity;
import com.bilolbek.ConsultingWebsite.services.DeleteDataService;
import com.bilolbek.ConsultingWebsite.services.EditDataService;
import com.bilolbek.ConsultingWebsite.services.FileService;
import com.bilolbek.ConsultingWebsite.services.GetDataService;
import com.bilolbek.ConsultingWebsite.services.SaveDataService;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {

    @Autowired
    private SaveDataService saveDataService;

    @Autowired
    private FileService fileService;

    @Autowired
    private DeleteDataService deleteDataService;

    @Autowired
    private GetDataService getDataService;

    @Autowired
    private EditDataService editDataService;
    
    @GetMapping("testAdmin")
    public ResponseEntity<Map<String, String>> testAdmin(){
        return ResponseEntity.ok(Map.of("message", "this is admin"));
    }


    /*
    
    {
        "country" : "string",
        "programType" : "string",
        "ageReq" : "string", //example: 28-35
        "degreeReq" : "string",
        "startDate" : "date",
        "regDeadline" : "date",
        "description" : "string",
        "link" : "string"
    }
    
     */
    @PostMapping("saveOpportunity")
    public ResponseEntity<Map<String, String>> saveOpportunity(@RequestBody Opportunity opportunity){
        return saveDataService.saveOpportunity(opportunity);
    }


    /*
        {
            "title" : "string",
            "startDate" : "date",
            "endDate" : "date",
            "description" : "string",
            "about" : "string",
            "language" : "string",
            "price" : "int"
        } 
     */
    @PostMapping("saveCourse")
    public ResponseEntity<Map<String, String>> saveCourse(@RequestBody Course course){
        return saveDataService.saveCourse(course);
    }

    @DeleteMapping("deleteCourse/{courseId}")
    public ResponseEntity<Map<String, String>> deleteCourse(@PathVariable long courseId){
        return deleteDataService.deleteCourse(courseId);
    }

    /*
        [
            {
                "code" : "string",
                "courseId" : "long"
            }
        ]
     */
    @PostMapping("saveRecordings")
    public ResponseEntity<Map<String, String>> saveRecordings(@RequestBody List<RecordingsDTO> recordings){
        return saveDataService.saveRecordings(recordings);
    }

    /*@PostMapping("saveResource")
    public ResponseEntity<Map<String, String>> saveResource(@RequestParam("file") MultipartFile file){
        try{
            return fileService.saveResource(file);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(Map.of("error", "Failed to save file"));
        }
    }*/

    /*@PostMapping("saveResources")
    public ResponseEntity<Map<String, String>> saveResources(@RequestParam("files") MultipartFile file){
        try{
            return fileService.saveResource(file);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(Map.of("error", "Failed to save file"));
        }
    }*/

    /*
        {
            "file" : "multipartfile",
            "courseId" : "long"
        }
     */
    @PostMapping("saveResource")
    public ResponseEntity<Map<String, String>> saveResource(
                            @RequestParam("file") MultipartFile file,
                            @RequestParam("courseId") long id
                        ){
        try{
            return fileService.saveResource(file, id);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(Map.of("error", "Failed to save file"));
        }
    }

    /*
        //both ids must be an existing record
        [
            {
                "id" : "user id long",
                "email" : "string",
                "courseId" : "long"
            }
        ]
     */
    @PostMapping("saveLearners")
    public ResponseEntity<Map<String, String>> saveLearners(@RequestBody List<LearnerDTO> learnerDTOs){
        return saveDataService.saveLearner(learnerDTOs);
    }

    

    /*
        {
            "id" : "long"
        }
     */
    @DeleteMapping("deleteDocRequest")
    public ResponseEntity<Map<String, String>> deleteDocRequest(@RequestBody DeleteRequestDTO deleteRequestDTO){
        return deleteDataService.deleteDocRequest(deleteRequestDTO.getId());
    }

    @GetMapping("getDocRequests")
    public List<DocCheckDTO> getAllDocRequests(){
        return getDataService.getDocRequests();
    }

    @GetMapping("getDocRequest/{requestId}")
    public DocCheckDTO getDocRequest(@PathVariable long requestId){
        return getDataService.getDocRequest(requestId);
    }

    @GetMapping("getRequestFiles/{requestId}")
    public List<DocCheckFiles> getRequestFiles(@PathVariable long requestId){
        return getDataService.getRequestFiles(requestId);
    }

    /*
        [
            {
                "docCheckId" : "long",
                "title" : "string",
                "note" : "string"
            }
        ]
    */
    @PostMapping("saveDocCheckNotes")
    public ResponseEntity<Map<String, String>> saveDocCheckNotes(@RequestBody List<DocCheckNotesDTO> notes){
        return saveDataService.saveDocCheckNotes(notes);
    }


    /*
        {
            "requestId" : "long",
            "status" : "DocStatus"
        }
    */
   @PutMapping("editDocCheckStatus")
   public ResponseEntity<Map<String, String>> editDocCheckStatus(@RequestBody DocCheckStatusDTO statusDTO){
    return editDataService.editDocRequestStatus(statusDTO);
   }
}
