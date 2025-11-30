package com.bilolbek.ConsultingWebsite.DTO;

public class VisaRequestDTO {

    private String telegram;

    private String whatsApp;

    private String email;

    private String country;

    private String purpose;

    public VisaRequestDTO(){
        this.telegram = "";
        this.whatsApp = "";
        this.email = "";
        this.country = "";
        this.purpose = "";
    }


    public String getTelegram() {
        return this.telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getWhatsApp() {
        return this.whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPurpose() {
        return this.purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }


}
