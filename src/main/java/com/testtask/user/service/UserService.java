package com.testtask.user.service;



import com.testtask.user.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User getUserById(Long id);

    User updateUser(User user);

    void deleteUser(Long userId);

    List<User> getAllUsers();

}