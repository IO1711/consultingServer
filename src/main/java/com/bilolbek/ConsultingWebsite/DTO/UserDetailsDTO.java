package com.bilolbek.ConsultingWebsite.DTO;

import com.bilolbek.ConsultingWebsite.enums.Role;

public class UserDetailsDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String avatar;
    private Role role;

    public UserDetailsDTO(String fname, String lname, String email, String avatar, Role role){
        this.firstname = fname;
        this.lastname = lname;
        this.email = email;
        this.avatar = avatar;
        this.role = role;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar(){
        return this.avatar;
    }

    public void setAvatar(String avatar){
        this.avatar = avatar;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
