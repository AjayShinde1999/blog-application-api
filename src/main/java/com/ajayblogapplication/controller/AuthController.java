package com.ajayblogapplication.controller;

import com.ajayblogapplication.payload.UserDto;
import com.ajayblogapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajayblogapplication.exception.ApiException;
import com.ajayblogapplication.payload.JwtAuthRequest;
import com.ajayblogapplication.payload.JwtAuthResponse;
import com.ajayblogapplication.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) {

		authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
		String token = jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);

		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {
			authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			System.out.println("Invalid Details!!");
			throw new ApiException("Invalid Username Or Password");

		}

	}

	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		UserDto registerUser = userService.registerNewUser(userDto);
		return new ResponseEntity<>(registerUser,HttpStatus.CREATED);
	}

}
