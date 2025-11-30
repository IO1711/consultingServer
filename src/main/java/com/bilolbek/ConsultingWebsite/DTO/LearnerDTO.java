package com.bilolbek.ConsultingWebsite.DTO;

public class LearnerDTO {

    private long id;
    
    private String email;

    private long courseId;

    public LearnerDTO(){

    }

    public LearnerDTO(long id, String email, long courseId){
        this.id = id;
        this.email = email;
        this.courseId = courseId;
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

}
