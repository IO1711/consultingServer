package com.bilolbek.ConsultingWebsite.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bilolbek.ConsultingWebsite.DTO.DocCheckStatusDTO;
import com.bilolbek.ConsultingWebsite.models.DocCheck;
import com.bilolbek.ConsultingWebsite.repositories.DocCheckRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class EditDataService {

    @Autowired
    private DocCheckRepo docCheckRepo;



    @Transactional
    public ResponseEntity<Map<String, String>> editDocRequestStatus(DocCheckStatusDTO statusDTO){
        DocCheck request = docCheckRepo.findById(statusDTO.getRequestId())
            .orElseThrow(() -> new EntityNotFoundException("DocCheck request with id " + statusDTO.getRequestId() + " not found"));
        
        request.setStatus(statusDTO.getStatus());

        return ResponseEntity.ok(Map.of("message", "Status upadted"));
    }
}
