package com.bilolbek.ConsultingWebsite.DTO;

import java.util.Date;

public class CourseDTO {
    
    private long id;
    private String title;
    private Date startDate;
    private Date endDate;
    private String description;
    private String about;
    private int lengthInWeek;
    private int currentProgress;
    private int learners;

    public CourseDTO(){

    }

    public CourseDTO(
        long id,
        String title,
        Date startDate,
        Date endDate,
        String description,
        String about,
        int lengthInWeek,
        int currentProgress,
        int learners
    ){
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.about = about;
        this.lengthInWeek = lengthInWeek;
        this.currentProgress = currentProgress;
        this.learners = learners;
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

    public int getCurrentProgress() {
        return this.currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }

    public int getLearners() {
        return this.learners;
    }

    public void setLearners(int learners) {
        this.learners = learners;
    }

}
