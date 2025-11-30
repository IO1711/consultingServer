package com.bilolbek.ConsultingWebsite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilolbek.ConsultingWebsite.models.AppUser;



public interface AppUserRepository extends JpaRepository<AppUser, Long>{
    AppUser findByEmail(String email);
}
