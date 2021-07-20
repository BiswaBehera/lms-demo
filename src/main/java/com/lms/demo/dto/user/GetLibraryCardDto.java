package com.lms.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetLibraryCardDto {

    @JsonProperty("library_id")
    private Long id;
}
