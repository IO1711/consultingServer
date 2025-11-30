package com.bilolbek.ConsultingWebsite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilolbek.ConsultingWebsite.models.DocCheck;
import com.bilolbek.ConsultingWebsite.models.DocCheckNotes;

public interface DocCheckNotesRepo extends JpaRepository<DocCheckNotes, Long>{
    List<DocCheckNotes> findByDocCheck(DocCheck docCheck);
}
