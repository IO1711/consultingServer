package com.bilolbek.ConsultingWebsite.models;

import java.util.Objects;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class JoinCourseRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private AppUser user;

    private String school;

    private String degree;

    private String major;

    private int year;
    
    public JoinCourseRequest(){}

    public JoinCourseRequest(Course course, AppUser user, String school, String degree, String major, int year){
        this.course = course;
        this.user = user;
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

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public AppUser getUser() {
        return this.user;
    }

    public void setUser(AppUser user) {
        this.user = user;
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


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        JoinCourseRequest request = (JoinCourseRequest) o;
        return id == request.getId();
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }



    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", course='" + getCourse() + "'" +
            ", user='" + getUser() + "'" +
            ", school='" + getSchool() + "'" +
            ", degree='" + getDegree() + "'" +
            ", major='" + getMajor() + "'" +
            ", year='" + getYear() + "'" +
            "}";
    }
    

}
