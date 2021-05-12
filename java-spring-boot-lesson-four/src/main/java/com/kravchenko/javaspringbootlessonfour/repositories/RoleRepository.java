package com.kravchenko.javaspringbootlessonfour.repositories;

import com.kravchenko.javaspringbootlessonfour.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
