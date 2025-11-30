package com.bilolbek.ConsultingWebsite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bilolbek.ConsultingWebsite.models.AppUser;
import com.bilolbek.ConsultingWebsite.repositories.AppUserRepository;
import com.bilolbek.ConsultingWebsite.utilities.UserPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private AppUserRepository appUserRepository;

    


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        return new UserPrincipal(user);
    }

}
