package com.jisdev.demo_ws.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jisdev.demo_ws.model.UserData;
import com.jisdev.demo_ws.model.UserResponse;
import static com.jisdev.demo_ws.utils.UserUtils.*;

@Service
public class UserService implements IUserService {

    private final Map<String, UserResponse> users = new HashMap<>();

    @Override
    public UserResponse createUser(UserData userData) {
        String id = generateUserId();
        UserResponse newUser = UserResponse.builder()
                .id(id)
                .firstName(userData.getFirstName())
                .lastName(userData.getLastName())
                .email(userData.getEmail())
                .password(userData.getPassword())
                .build();
        users.put(id, newUser);
        return newUser;
    }

    @Override
    public UserResponse getUserById(String id) {
        return users.get(id);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return List.copyOf(users.values());
    }

    @Override
    public UserResponse updateUser(String id, UserData updated) {
        UserResponse existing = users.get(id);
        if (existing == null) {
            return null;
        }
        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setEmail(updated.getEmail());
        users.put(id, existing);
        return existing;
    }

    @Override
    public boolean deleteUser(String id) {
        UserResponse removed = users.remove(id);
        return removed != null;
    }

}
