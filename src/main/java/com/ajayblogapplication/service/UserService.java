package com.ajayblogapplication.service;

import java.util.List;

import com.ajayblogapplication.payload.UserDto;

public interface UserService {

	UserDto registerNewUser(UserDto userDto);

	UserDto createUser(UserDto userDto);

	List<UserDto> getAllUser();

	UserDto getUserById(Integer userId);

	UserDto updateUser(UserDto userDto, Integer userId);

	void deleteUser(Integer userId);

}
