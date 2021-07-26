package com.lms.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.demo.dao.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddUserResponse {
    @JsonProperty("library_id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("contact_number")
    private String contactNumber;

    @JsonProperty("message")
    private String message;

    public AddUserResponse(User user) {
        this.setName(user.getName());
        this.setContactNumber(user.getContactNumber());
        this.setId(user.getId());
    }
}
