package com.ajayblogapplication.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ajayblogapplication.config.AppConstants;
import com.ajayblogapplication.entities.Role;
import com.ajayblogapplication.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ajayblogapplication.entities.User;
import com.ajayblogapplication.exception.ResourceNotFoundException;
import com.ajayblogapplication.payload.UserDto;
import com.ajayblogapplication.repositories.UserRepository;
import com.ajayblogapplication.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = mapToEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepo.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);
        User newSave = userRepo.save(user);
        return mapToDto(newSave);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapToEntity(userDto);
        User newUser = userRepo.save(user);
        return mapToDto(newUser);

    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepo.findAll();
        return users.stream().map(user -> mapToDto(user)).collect(Collectors.toList());

    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return mapToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = userRepo.save(user);
        return mapToDto(updatedUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepo.delete(user);
    }

    public User mapToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
//		return user;
    }

    public UserDto mapToDto(User user) {
        return modelMapper.map(user, UserDto.class);

//		UserDto dto = new UserDto();
//		dto.setId(user.getId());
//		dto.setName(user.getName());
//		dto.setEmail(user.getEmail());
//		dto.setPassword(user.getPassword());
//		dto.setAbout(user.getAbout());
//		return dto;
    }

}
