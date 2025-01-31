package com.app.Controller;

import java.util.List;

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

//	------------ X ------------ X ------------ X ------------ X ------------ X ------------ X ------------

	// Endpoint to update existing credential
	@PutMapping(name="updateCredential", path="/{id}")
	public ResponseEntity<CredentialsResponseDto> updateCredentials(@PathVariable("id") Long id, @RequestBody Credentials credential){
		CredentialsResponseDto updated_credential = credentialService.updateCredentialsService(id,credential);
		return ResponseEntity.status(HttpStatus.OK).body(updated_credential);
	}

	// sign in (No security added yet)
//	@PostMapping(path="",name = "PostCredentials",consumes = {"application/json","application/xml"})
//	public ResponseEntity<CredentialsResponseDto> postCredentials(@Valid @RequestBody CredentialsRequestDto credential) {
//		CredentialsResponseDto response_credential = credentialService.addCredentials(credential);
//		return ResponseEntity.status(HttpStatus.CREATED).body(response_credential);
//	}

}
