package com.example.home_crud.Service.user;

import com.example.home_crud.DTO.UserDto;
import com.example.home_crud.Model.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
