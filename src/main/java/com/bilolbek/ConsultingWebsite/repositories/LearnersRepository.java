package com.bilolbek.ConsultingWebsite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilolbek.ConsultingWebsite.models.AppUser;
import com.bilolbek.ConsultingWebsite.models.Course;
import com.bilolbek.ConsultingWebsite.models.Learners;

public interface LearnersRepository extends JpaRepository<Learners, Long>{
    List<Learners> findByCourse(Course course);
    List<Learners> findByStudent(AppUser appUser);
}
