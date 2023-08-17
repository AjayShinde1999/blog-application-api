package com.ajayblogapplication;

import com.ajayblogapplication.config.AppConstants;
import com.ajayblogapplication.entities.Role;
import com.ajayblogapplication.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogApplicationApisApplication implements  CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplicationApisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepo;


	@Override
	public void run(String... args) throws Exception {
		Role role = new Role();
		role.setId(AppConstants.ADMIN_USER);
		role.setName("ROLE_ADMIN");

		Role role1 = new Role();
		role1.setId(AppConstants.NORMAL_USER);
		role1.setName("ROLE_NORMAL");

		roleRepo.save(role);
		roleRepo.save(role1);

	}
}
