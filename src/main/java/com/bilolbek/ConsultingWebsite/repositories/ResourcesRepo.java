package com.bilolbek.ConsultingWebsite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilolbek.ConsultingWebsite.models.Course;
import com.bilolbek.ConsultingWebsite.models.Resources;

public interface ResourcesRepo extends JpaRepository<Resources, Long>{
    List<Resources> findByCourse(Course course);
}
