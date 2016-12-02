package com.heitian.ssm.service;

import com.heitian.ssm.model.User;

import java.util.List;

/**
 * Created by LanTing on 2016/11/24.
 */
public interface UserService {

    List<User> getAllUser();

    User getUserByPhoneOrEmail(String emailOrPhone, Short state);

    User getUserById(Long userId);
}
