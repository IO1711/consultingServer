package com.bilolbek.ConsultingWebsite.models;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String country;

    private String programType;

    private String ageReq;

    private String degreeReq;

    private Date startDate;

    private Date regDeadline;

    private String description;

    private String link;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProgramType() {
        return this.programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public String getAgeReq() {
        return this.ageReq;
    }

    public void setAgeReq(String ageReq) {
        this.ageReq = ageReq;
    }

    public String getDegreeReq() {
        return this.degreeReq;
    }

    public void setDegreeReq(String degreeReq) {
        this.degreeReq = degreeReq;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getRegDeadline() {
        return this.regDeadline;
    }

    public void setRegDeadline(Date regDeadline) {
        this.regDeadline = regDeadline;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        Opportunity opportunity = (Opportunity) o;
        return id==opportunity.id;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", country='" + getCountry() + "'" +
            ", programType='" + getProgramType() + "'" +
            ", ageReq='" + getAgeReq() + "'" +
            ", degreeReq='" + getDegreeReq() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", regDeadline='" + getRegDeadline() + "'" +
            ", description='" + getDescription() + "'" +
            ", link='" + getLink() + "'" +
            "}";
    }

}
