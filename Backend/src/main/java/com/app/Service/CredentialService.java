package com.app.Service;

import java.util.Collections;
import java.util.List;

import com.app.DTO.Credentials.CredentialsResponseDto;
import com.app.Entity.User;
import com.app.Exception.ExceptionTypes.ResourceNotFoundException;
import com.app.Mapper.CredentialMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.app.Entity.Credentials;
import com.app.Repository.CredentialsRepository;

@Service
public class CredentialService {

	private final CredentialsRepository credentialsRepository;

	public CredentialService(CredentialsRepository credentialsRepository) {
		this.credentialsRepository = credentialsRepository;
	}

	// post credentials service
	@Transactional
	public CredentialsResponseDto addCredentials(Credentials credential) {
		try {
			Credentials credentials = credentialsRepository.save(credential);
			return CredentialMapper.ToCredentialResponseDto(credentials);
		} catch (Exception e) {
			throw new RuntimeException("Failed to add credentials", e);
		}
	}

	// get credentials by id service
	public CredentialsResponseDto getCredentialsById(Long credential_id) {
		try {
			Credentials credential = credentialsRepository
									.findById(credential_id)
									.orElse(new Credentials());
			return CredentialMapper.ToCredentialResponseDto(credential);
		} catch(Exception e) {
			 throw new RuntimeException("Failed to retrieve user with id: "+credential_id, e);
		}
	}

	// get all credentials service
	public List<CredentialsResponseDto> getAllCredentials() {
		try {
			List<Credentials> allCredentials = credentialsRepository.findAll();

			List<CredentialsResponseDto> credentialDto = allCredentials.stream()
					.map(CredentialMapper::ToCredentialResponseDto)
					.toList();

			return allCredentials.isEmpty() ? Collections.emptyList() : credentialDto;
		} catch(Exception e) {
            throw new RuntimeException("Failed to retrieve users", e);
		}
	}

	// update the credential by id
	@Transactional
    public CredentialsResponseDto updateCredentialsService(Long id, Credentials credential) {
		if(id == null) {
			throw new IllegalArgumentException("User id cannot be null");
		}
		try{
			Credentials temp_credential = credentialsRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Invalid user id:" + id));

			if(credential != null) {
				if(credential.getEmail() != null) {
					temp_credential.setEmail(credential.getEmail());
				}
				if(credential.getPassword() != null) {
					temp_credential.setPassword(credential.getPassword());
				}
			}

			return CredentialMapper.ToCredentialResponseDto(credentialsRepository.save(temp_credential));
		} catch(Exception e) {
			throw new RuntimeException("Failed to update credentials", e);
		}
    }

	@Transactional
	public CredentialsResponseDto deleteCredentialById(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("User id cannot be null");
		}

		Credentials credential = credentialsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Credential not found with id: " + id));

		if (credential.getUser() != null) {
			User user = credential.getUser();
			user.setCredentials(null);  // Break bidirectional relationship
			credential.setUser(null);
		}
		credentialsRepository.delete(credential);

		if (credentialsRepository.existsById(id)) {
			throw new RuntimeException("Failed to delete credential");
		}
		return CredentialMapper.ToCredentialResponseDto(credential);
	}
}
