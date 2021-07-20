package com.lms.demo.dto.mapper;

import com.lms.demo.dao.model.User;
import com.lms.demo.dto.user.AddUserDto;
import com.lms.demo.error.ErrorResponseMessages;
import com.lms.demo.error.IllegalPropertyValueException;

import java.util.Objects;

//from dto to entity
public class UserMapper {
    public User fromAddUser(AddUserDto addUserDto) throws IllegalPropertyValueException {
        User user = new User();

        //maps the name
        user.setName(addUserDto.getName());

        //maps the contact number
        user.setContactNumber(addUserDto.getContactNumber());

        // handle this later
        user.setPassword(addUserDto.getPassword());

        //assigns admin privilege
        if(Objects.isNull(addUserDto.getIsAdmin())) {
            user.setIsAdmin(false);
        } else {
            user.setIsAdmin(addUserDto.getIsAdmin());
        }

        return user;
    }
}
