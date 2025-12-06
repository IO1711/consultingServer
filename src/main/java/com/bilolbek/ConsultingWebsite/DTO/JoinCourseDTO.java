package com.bilolbek.ConsultingWebsite.DTO;

public class JoinCourseDTO {

    private long courseId;

    private String school;

    private String degree;

    private String major;

    private int year;

    public JoinCourseDTO(){}

    public JoinCourseDTO(long courseId, String school, String degree, String major, int year){
        this.courseId = courseId;
        this.school = school;
        this.degree = degree;
        this.major = major;
        this.year = year;
    }


    public long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
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
