package com.bilolbek.ConsultingWebsite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilolbek.ConsultingWebsite.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
