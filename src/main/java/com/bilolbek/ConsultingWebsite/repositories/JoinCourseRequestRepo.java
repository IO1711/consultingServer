package com.bilolbek.ConsultingWebsite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilolbek.ConsultingWebsite.models.AppUser;
import com.bilolbek.ConsultingWebsite.models.Course;
import com.bilolbek.ConsultingWebsite.models.JoinCourseRequest;

public interface JoinCourseRequestRepo extends JpaRepository<JoinCourseRequest, Long>{
    List<JoinCourseRequest> findByCourse(Course course);
    JoinCourseRequest findByCourseAndUser(Course course, AppUser user);
}
