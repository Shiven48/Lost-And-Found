package com.app.Service;

import java.util.Collections;
import java.util.List;

import com.app.Exception.ExceptionTypes.ResourceNotFoundException;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.stereotype.Service;

import com.app.Entity.Credentials;
import com.app.Entity.UserFound;
import com.app.Repository.CredentialsRepository;

@Service
public class CredentialService {

	private CredentialsRepository credentialsRepository;

	public CredentialService(CredentialsRepository credentialsRepository) {
		this.credentialsRepository = credentialsRepository;
	}

	// post credentials service
	public Credentials addCredentials(Credentials credential) {
		try {
			return credentialsRepository.save(credential);
		} catch (Exception e) {
			throw new RuntimeException("Failed to add credentials", e);
		}
	}

	// get credentials by id service
	public Credentials getCredentialsById(Long credential_id) {
		try {
			return credentialsRepository
									.findById(credential_id)
									.orElse(new Credentials());
		} catch(Exception e) {
			 throw new RuntimeException("Failed to retrieve user with id: "+credential_id, e);
		}

	}

	// get all credentials service
	public List<Credentials> getAllCredentials() {
		try {
			List<Credentials> allCredentials = credentialsRepository.findAll();
			return allCredentials.isEmpty() ? Collections.emptyList() : allCredentials;	
		} catch(Exception e) {
            throw new RuntimeException("Failed to retrieve users", e);
		}
	}

	// update the credential by id
    public Credentials updateCredentialsService(Long id, Credentials credential) {
		if(id == null) {
			throw new IllegalArgumentException("User id cannot be null");
		}
		try{
			Credentials resp_credential = credentialsRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Invalid user id:" + id));
			if(credential != null) {
				if(credential.getEmail() != null) {
					resp_credential.setEmail(credential.getEmail());
				}
				if(credential.getPassword() != null) {
					resp_credential.setPassword(credential.getPassword());
				}
			}
			return credentialsRepository.save(resp_credential);
		} catch(Exception e) {
			throw new RuntimeException("Failed to update credentials", e);
		}
    }
}
