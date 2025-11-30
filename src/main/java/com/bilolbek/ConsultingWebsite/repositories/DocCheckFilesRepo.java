package com.bilolbek.ConsultingWebsite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilolbek.ConsultingWebsite.models.DocCheck;
import com.bilolbek.ConsultingWebsite.models.DocCheckFiles;

public interface DocCheckFilesRepo extends JpaRepository<DocCheckFiles, Long>{
    List<DocCheckFiles> findByDocCheck(DocCheck docCheck);
}
