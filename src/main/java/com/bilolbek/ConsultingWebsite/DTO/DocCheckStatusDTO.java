package com.bilolbek.ConsultingWebsite.DTO;

import com.bilolbek.ConsultingWebsite.enums.DocStatus;

public class DocCheckStatusDTO {

    private long requestId;

    private DocStatus status;

    public DocCheckStatusDTO(){}

    public DocCheckStatusDTO(long id, DocStatus status){
        this.requestId = id;
        this.status = status;
    }


    public long getRequestId() {
        return this.requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public DocStatus getStatus() {
        return this.status;
    }

    public void setStatus(DocStatus status) {
        this.status = status;
    }

}
