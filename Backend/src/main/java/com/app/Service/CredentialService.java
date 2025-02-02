package com.app.Service;

import java.util.Collections;
import java.util.List;

import com.app.Models.DTO.Credentials.CredentialsRequestDto;
import com.app.Models.DTO.Credentials.CredentialsResponseDto;
import com.app.Models.Mapper.CredentialMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.app.Models.Entities.Credentials;
import com.app.Repository.CredentialsRepository;

@Service
public class CredentialService {

	private final CredentialsRepository credentialsRepository;
	private final CredentialMapper credentialMapper;

	public CredentialService(
			CredentialsRepository credentialsRepository,
			CredentialMapper mapper
	) {
		this.credentialsRepository = credentialsRepository;
		this.credentialMapper = mapper;
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

	// update the credential by id
	@Transactional
    public CredentialsResponseDto updateCredentialsService(Long id, CredentialsRequestDto credential) {
		if(!checkIfIdNull(id)) {
			throw new IllegalArgumentException("User id cannot be null");
		}
		Credentials credentials = fetchCredentialsById(id);
		credentials = credentialMapper.CredReqToCredentials(credentials,credential);
		return CredentialMapper.ToCredentialResponseDto(credentialsRepository.save(credentials));
    }

	private boolean checkIfIdNull(Long id){
		return id != null;
	}

	private Credentials fetchCredentialsById(Long id){
		return credentialsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Unable to fetch credentials"));
	}
}
