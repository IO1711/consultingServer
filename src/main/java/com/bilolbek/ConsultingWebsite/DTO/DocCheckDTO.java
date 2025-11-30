package com.bilolbek.ConsultingWebsite.DTO;

import java.util.Date;
import java.util.List;

import com.bilolbek.ConsultingWebsite.enums.DocStatus;
import com.bilolbek.ConsultingWebsite.models.DocCheckFiles;

public class DocCheckDTO {

    private long id;

    private String program;

    private Date submittedAt;

    private DocStatus status;

    private String comment;

    private String firstname;

    private String lastname;

    private int files;

    private List<DocCheckFiles> allFiles;

    public DocCheckDTO(){}

    public DocCheckDTO(long id, String program, Date submittedAt, DocStatus status, String comment, int files, List<DocCheckFiles> allFiles){
        this.id = id;
        this.program = program;
        this.submittedAt = submittedAt;
        this.status = status;
        this.comment = comment;
        this.files = files;
        this.allFiles = allFiles;
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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


    public int getFiles() {
        return this.files;
    }

    public void setFiles(int files) {
        this.files = files;
    }


    public List<DocCheckFiles> getAllFiles() {
        return this.allFiles;
    }

    public void setAllFiles(List<DocCheckFiles> allFiles) {
        this.allFiles = allFiles;
    }

}
