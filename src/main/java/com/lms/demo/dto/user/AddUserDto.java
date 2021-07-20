package com.lms.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddUserDto {
//    @JsonProperty("admin_id")
//    private Long adminId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("password")
    private String password;

    @JsonProperty("contact_number")
    private String contactNumber;

    @JsonProperty("is_admin")
    private Boolean isAdmin;
}
