package com.bilolbek.ConsultingWebsite.models;


import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String title;

    private Date startDate;

    private Date endDate;

    private String description;

    private String about;

    private String language;

    private int price;

    private int lengthInWeek;

    public Course(){

    }

    public Course(String title, Date startDate, Date endDate, String description, String about, String language, int price){
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.about = about;
        this.language = language;
        this.price = price;

        long diffMillis = endDate.getTime() - startDate.getTime();
        long diffDays = diffMillis/(1000 * 60 * 60 * 24);
        this.lengthInWeek = (int) Math.ceil(diffDays/7.0);
        
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbout() {
        return this.about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getLengthInWeek() {
        return this.lengthInWeek;
    }

    public void setLengthInWeek(int lengthInWeek) {
        this.lengthInWeek = lengthInWeek;
    }


    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Transient
    public int getCurrentProgress() {
        
        Date now = new Date();

        if(now.before(startDate)) return 0;
        if(now.after(endDate)) return 100;

        long totalMillis = endDate.getTime() - startDate.getTime();
        long elapseMillis = now.getTime() - startDate.getTime();

        return (int) Math.round(elapseMillis * 100.0/totalMillis);
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id==course.id;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }



    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", about='" + getAbout() + "'" +
            ", language='" + getLanguage() + "'" +
            ", price='" + getPrice() + "'" +
            ", lengthInWeek='" + getLengthInWeek() + "'" +
            ", currentProgress='" + getCurrentProgress() + "'" +
            "}";
    }
    

}
