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
public class DocCheckFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "request_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DocCheck docCheck;

    private String filename;

    public DocCheckFiles(){}

    public DocCheckFiles(DocCheck docCheck, String filename){
        this.docCheck = docCheck;
        this.filename = filename;
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DocCheck getDocCheck() {
        return this.docCheck;
    }

    public void setDocCheck(DocCheck docCheck) {
        this.docCheck = docCheck;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;
        DocCheckFiles docCheckFiles = (DocCheckFiles) o;
        return id==docCheckFiles.id;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", docCheck='" + getDocCheck() + "'" +
            ", filename='" + getFilename() + "'" +
            "}";
    }

}
