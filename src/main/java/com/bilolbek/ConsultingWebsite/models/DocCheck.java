package com.bilolbek.ConsultingWebsite.models;

import java.util.Date;
import java.util.Objects;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.bilolbek.ConsultingWebsite.enums.DocStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DocCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private AppUser user;

    private String program;

    private Date submittedAt;

    private DocStatus status;

    private String comment;

    public DocCheck(){}

    public DocCheck(AppUser user, String program, String comment){
        this.user = user;
        this.program = program;
        this.submittedAt = new Date();
        this.status = DocStatus.PENDING;
        this.comment = comment;
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return this.user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getProgram() {
        return this.program;
    }

    public void setProgram(String program) {
        this.program = program;
    }


    public Date getSubmittedAt() {
        return this.submittedAt;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }

    public DocStatus getStatus() {
        return this.status;
    }

    public void setStatus(DocStatus status) {
        this.status = status;
    }

    public String getCommennt(){
        return this.comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }


    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return true;
        DocCheck docCheck = (DocCheck) o;
        return id==docCheck.id;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", program='" + getProgram() + "'" +
            ", submittedAt'" + getSubmittedAt() + "'" +
            ", status'" + getStatus() + "'" + 
            "}";
    }

    
    
}
