package com.bilolbek.ConsultingWebsite.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bilolbek.ConsultingWebsite.DTO.RegisterDTO;
import com.bilolbek.ConsultingWebsite.DTO.UserDetailsDTO;
import com.bilolbek.ConsultingWebsite.enums.Role;
import com.bilolbek.ConsultingWebsite.models.AppUser;
import com.bilolbek.ConsultingWebsite.repositories.AppUserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AppUserRepository appUserRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Transactional
    public ResponseEntity<Map<String, String>> register(RegisterDTO registerDTO){
        AppUser newUser = new AppUser();

        newUser.setFirstname(registerDTO.getFirstname());
        newUser.setLastname(registerDTO.getLastname());
        newUser.setAvatar(registerDTO.getAvatar());
        newUser.setEmail(registerDTO.getEmail());
        newUser.setRole(Role.USER);

        newUser.setPassword(encoder.encode(registerDTO.getPassword()));

        appUserRepository.save(newUser);

        return ResponseEntity.ok(Map.of("message", "Registration successful"));
    }


    public ResponseEntity<Map<String, String>> verify(String email, String pword){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, pword));

        if(authentication.isAuthenticated())
            return ResponseEntity.ok(Map.of("token", jwtService.generateToken(email)));

        return ResponseEntity.ok(Map.of("message", "fail"));
    }

    public UserDetailsDTO getUserDetails(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        AppUser user = appUserRepository.findByEmail(email);

        UserDetailsDTO currentUser = new UserDetailsDTO(user.getFirstname(), user.getLastname(), user.getEmail(), user.getAvatar(), user.getRole());

        return currentUser;
    }

    public ResponseEntity<Map<String, String>> registerAdmin(RegisterDTO registerDTO){
        AppUser newAdmin = new AppUser();
        newAdmin.setFirstname(registerDTO.getFirstname());
        newAdmin.setLastname(registerDTO.getLastname());
        newAdmin.setAvatar(registerDTO.getAvatar());
        newAdmin.setEmail(registerDTO.getEmail());
        newAdmin.setRole(Role.ADMIN);

        newAdmin.setPassword(encoder.encode(registerDTO.getPassword()));

        appUserRepository.save(newAdmin);

        return ResponseEntity.ok(Map.of("message", "admin added"));

    }
}
