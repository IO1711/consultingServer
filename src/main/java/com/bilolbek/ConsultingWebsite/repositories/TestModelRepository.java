package com.bilolbek.ConsultingWebsite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilolbek.ConsultingWebsite.models.TestModel;

public interface TestModelRepository extends JpaRepository<TestModel, Long> {

}
