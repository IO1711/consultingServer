package com.bilolbek.ConsultingWebsite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilolbek.ConsultingWebsite.models.Opportunity;

public interface OpportunityRepo extends JpaRepository<Opportunity, Long>{

}
