package com.kushalshrestha.springboot.repository;

import com.kushalshrestha.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
