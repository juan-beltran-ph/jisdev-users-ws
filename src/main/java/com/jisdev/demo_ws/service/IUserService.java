package com.jisdev.demo_ws.service;

import java.util.List;

import com.jisdev.demo_ws.model.UserData;
import com.jisdev.demo_ws.model.UserResponse;

public interface IUserService {

    UserResponse createUser(UserData userData);
    UserResponse getUserById(String id);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(String id, UserData userData);
    boolean deleteUser(String id);

}
