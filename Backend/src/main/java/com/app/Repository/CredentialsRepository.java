package com.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.Models.Entities.Credentials;

import java.util.Optional;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    Optional<Credentials> findByName(String name);
}
