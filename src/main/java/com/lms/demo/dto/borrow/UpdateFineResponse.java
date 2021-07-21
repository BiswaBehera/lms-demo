package com.lms.demo.dto.borrow;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateFineResponse {
    @JsonProperty("message")
    private String message;

    public UpdateFineResponse() {
        this.setMessage("all fines are refreshed");
    }
}
