package com.bilolbek.ConsultingWebsite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilolbek.ConsultingWebsite.models.AppUser;
import com.bilolbek.ConsultingWebsite.models.VisaRequest;

public interface VisaRequestRepo extends JpaRepository<VisaRequest, Long>{
    List<VisaRequest> findByUser(AppUser user);
}
