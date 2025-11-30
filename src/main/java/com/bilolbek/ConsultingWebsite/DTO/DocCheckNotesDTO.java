package com.bilolbek.ConsultingWebsite.DTO;

public class DocCheckNotesDTO {

    private long docCheckId;

    private String title;

    private String note;

    public DocCheckNotesDTO(){}

    public DocCheckNotesDTO(long docCheckId, String title, String note){
        this.docCheckId = docCheckId;
        this.title = title;
        this.note = note;
    }


    public long getDocCheckId() {
        return this.docCheckId;
    }

    public void setDocCheckId(long docCheckId) {
        this.docCheckId = docCheckId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
