package com.bilolbek.ConsultingWebsite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilolbek.ConsultingWebsite.models.Course;
import com.bilolbek.ConsultingWebsite.models.Recordings;

public interface RecordingsRepo extends JpaRepository<Recordings, Long>{
    List<Recordings> findByCourse(Course course);
}
