package com.app.Repository;

import com.app.Models.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Models.Entities.Credentials;
//import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    Optional<User> findByName(String name);
}
