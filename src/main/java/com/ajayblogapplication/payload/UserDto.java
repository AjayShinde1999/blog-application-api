package com.ajayblogapplication.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto {

	private Integer userId;

	@NotEmpty(message = "Name is required")
	private String name;

	@Email(message = "Enter valid email")
	private String email;

	@NotEmpty(message = "Password is required")
	@Size(min = 4, max = 8, message = "minimum size is 4 and max size is 8")
	private String password;

	@NotEmpty(message = "Not be empty")
	private String about;

	private Set<RoleDto> roles = new HashSet<>();

//	@JsonIgnore
//	public String getPassword() {
//		return password;
//	}


}
