package com.rungroop.web.services.impl;

import com.rungroop.web.dto.RegistrationDto;
import com.rungroop.web.models.Role;
import com.rungroop.web.models.UserEntity;
import com.rungroop.web.repository.RoleRepository;
import com.rungroop.web.repository.UserRepository;
import com.rungroop.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository _userRepository;
    private RoleRepository _roleRepository;
    @Autowired
    public UserServiceImpl(UserRepository _userRepository, RoleRepository _roleRepository) {
        this._userRepository = _userRepository;
        this._roleRepository = _roleRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        Role role = _roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        _userRepository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return _userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return _userRepository.findByUsername(username);
    }
}
