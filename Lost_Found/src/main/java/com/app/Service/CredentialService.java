package com.app.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.Entity.Credentials;
import com.app.Repository.CredentialsRepository;

@Service
public class CredentialService {

	private CredentialsRepository credentialsRepository;

	public CredentialService(CredentialsRepository credentialsRepository) {
		this.credentialsRepository = credentialsRepository;
	}

	public Credentials addCredentials(Credentials credential) {
		try {
			return credentialsRepository.save(credential);
		} catch (Exception e) {
			throw new RuntimeException("Failed to add credentials", e);
		}
	}
	
	public Credentials getCredentialsById(Long credential_id) {
		try {
			return credentialsRepository
									.findById(credential_id)
									.orElse(new Credentials());
		} catch(Exception e) {
			 throw new RuntimeException("Failed to retrieve user with id: "+credential_id, e);
		}

	}

	public List<Credentials> getAllCredentials() {
		// Add some logica
		return null;
	}

}
