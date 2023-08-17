package com.ajayblogapplication.repositories;

import com.ajayblogapplication.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
