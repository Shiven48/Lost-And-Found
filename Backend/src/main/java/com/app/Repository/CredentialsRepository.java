package com.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Entity.Credentials;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

}
