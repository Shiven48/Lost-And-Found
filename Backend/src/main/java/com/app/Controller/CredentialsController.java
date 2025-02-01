package com.app.Controller;

import java.util.List;

import com.app.Models.DTO.Credentials.CredentialsRequestDto;
import com.app.Models.DTO.Credentials.CredentialsResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.Models.Entities.Credentials;
import com.app.Service.CredentialService;

@RequestMapping("/api/credentials")
@RestController
public class CredentialsController {

	private final CredentialService credentialService;
	
	public CredentialsController(CredentialService credentialService) {
		this.credentialService = credentialService;
	}

	// log in (No security added yet)
	@GetMapping(path="/{id}",name = "GetCredentials")
	public ResponseEntity<CredentialsResponseDto> GetCredentialsById(@PathVariable("id") Long id) {
		CredentialsResponseDto credentialDto = credentialService.getCredentialsById(id);
		return ResponseEntity.status(HttpStatus.OK).body(credentialDto);
	}

	// get all credentials
	@GetMapping(path="",name = "GetCredentials")
	public ResponseEntity<List<CredentialsResponseDto>> GetCredentials() {
		List<CredentialsResponseDto> response_AllCredential = credentialService.getAllCredentials();
		return ResponseEntity.status(HttpStatus.OK).body(response_AllCredential);
	}

	// Endpoint to update existing credential
	@PutMapping(name="updateCredential", path="/{id}")
	public ResponseEntity<CredentialsResponseDto> updateCredentials(@PathVariable("id") Long id, @RequestBody CredentialsRequestDto credential){
		CredentialsResponseDto updated_credential = credentialService.updateCredentialsService(id,credential);
		return ResponseEntity.status(HttpStatus.OK).body(updated_credential);
	}
}
