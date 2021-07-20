package com.lms.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.demo.dao.model.BorrowDetails;
import com.lms.demo.dao.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LibraryCardResponse {

    @JsonProperty("library_id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("contact_no")
    private String contactNumber;

    @JsonProperty("list_of_active_borrows")
    private List<BorrowDetailsForLibraryCardResponse> listOfActiveBorrows;

    public LibraryCardResponse(User user, List<BorrowDetails> activeBorrowDetailsList){
        this.setId(user.getId());
        this.setContactNumber(user.getContactNumber());
        this.setName(user.getName());

        this.setListOfActiveBorrows(new ArrayList<>());
        for(BorrowDetails bd: activeBorrowDetailsList) {
            this.listOfActiveBorrows.add(new BorrowDetailsForLibraryCardResponse(bd));
        }
    }
}
