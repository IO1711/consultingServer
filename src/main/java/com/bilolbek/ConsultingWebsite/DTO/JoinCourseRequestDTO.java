package com.bilolbek.ConsultingWebsite.DTO;

public class JoinCourseRequestDTO {

    private long id;
    private long userId;
    private String email;
    private String firstname;
    private String lastname;
    private String school;
    private String degree;
    private String major;
    private int year;

    public JoinCourseRequestDTO(){}

    public JoinCourseRequestDTO(long id, long userId, String email, String firstname, String lastname, String school, String degree, String major, int year){
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.school = school;
        this.degree = degree;
        this.major = major;
        this.year = year;
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSchool() {
        return this.school;
    }

    public void setSchool(String school) {
        this.school = school;
    }


    public String getDegree() {
        return this.degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }


    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
