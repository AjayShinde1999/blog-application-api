package com.ajayblogapplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ajayblogapplication.entities.User;
import com.ajayblogapplication.exception.ApiException;
import com.ajayblogapplication.exception.ResourceNotFoundException;
import com.ajayblogapplication.payload.ApiResponse;
import com.ajayblogapplication.repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username)
				.orElseThrow(() -> new ApiException("Invalid username"));

		return user;
	}

}
