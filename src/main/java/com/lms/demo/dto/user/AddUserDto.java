package com.lms.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.demo.error.ErrorResponseMessages;
import com.lms.demo.error.IllegalPropertyValueException;
import lombok.Data;

import java.util.Objects;

@Data
public class AddUserDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("password")
    private String password;

    @JsonProperty("contact_number")
    private String contactNumber;

    @JsonProperty("is_admin")
    private Boolean isAdmin;

    public void nullCheckForRequiredProperties() throws IllegalPropertyValueException {
        //null check for name
        if(Objects.isNull(this.getName())) {
            throw new IllegalPropertyValueException(ErrorResponseMessages.nullNameValueForUser);
        }
        //null check for contact number
        if(Objects.isNull(this.getContactNumber())) {
            throw new IllegalPropertyValueException(ErrorResponseMessages.nullContactValueForUser);
        }
    }
}
