package com.lms.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddUserDto {
    @JsonProperty("name")
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    private String name;

    @JsonProperty("password")
    private String password;

    @JsonProperty("contact_number")
    @NotNull(message = "Contact Number is required")
    @NotBlank(message = "Contact Number is required")
    @Size(min = 10, max = 10, message = "Contact number must have 10 digits")
    private String contactNumber;

    @JsonProperty("is_admin")
    private Boolean isAdmin;
}
