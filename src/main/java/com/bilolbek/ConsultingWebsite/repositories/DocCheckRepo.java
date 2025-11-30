package com.bilolbek.ConsultingWebsite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilolbek.ConsultingWebsite.models.AppUser;
import com.bilolbek.ConsultingWebsite.models.DocCheck;

public interface DocCheckRepo extends JpaRepository<DocCheck, Long>{
    List<DocCheck> findByUser(AppUser user);
}
