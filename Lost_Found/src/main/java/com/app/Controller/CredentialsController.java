package com.app.Controller;

import java.util.List;

import com.app.DTO.Credentials.CredentialsResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.Entity.Credentials;
import com.app.Service.CredentialService;


// This is for credentials
@RequestMapping("api/")
@RestController
public class CredentialsController {

	private final CredentialService credentialService;
	
	public CredentialsController(CredentialService credentialService) {
		this.credentialService = credentialService;
	}
	
	// sign in (No security added yet)
	@PostMapping(path="credentials",name = "PostCredentials",consumes = {"application/json","application/xml"})
	public ResponseEntity<CredentialsResponseDto> postCredentials(@RequestBody Credentials credential) {
		CredentialsResponseDto response_credential = credentialService.addCredentials(credential);
		return ResponseEntity.status(HttpStatus.CREATED).body(response_credential);
	}

	// log in (No security added yet)
	@GetMapping(path="credentials/{id}",name = "GetCredentials")
	public ResponseEntity<CredentialsResponseDto> GetCredentialsById(@PathVariable("id") Long id) {
		CredentialsResponseDto credentialDto = credentialService.getCredentialsById(id);
		return ResponseEntity.status(HttpStatus.OK).body(credentialDto);
	}

	// get all credentials
	@GetMapping(path="credentials",name = "GetCredentials")
	public ResponseEntity<List<CredentialsResponseDto>> GetCredentials() {
		List<CredentialsResponseDto> response_AllCredential = credentialService.getAllCredentials();
		return ResponseEntity.status(HttpStatus.OK).body(response_AllCredential);
	}

	// Endpoint to update existing credential
	@PutMapping(name="updateCredential", path="credentials/{id}")
	public ResponseEntity<CredentialsResponseDto> updateCredentials(@PathVariable("id") Long id, @RequestBody Credentials credential){
		CredentialsResponseDto updated_credential = credentialService.updateCredentialsService(id,credential);
		return ResponseEntity.status(HttpStatus.OK).body(updated_credential);
	}

	// Endpoint to delete a credential
	@DeleteMapping(name="deleteCredential",path="credentials/{id}")
	public ResponseEntity<CredentialsResponseDto> deleteCredentials(@PathVariable("id") Long id) {
		CredentialsResponseDto deleted_credential = credentialService.deleteCredentialById(id);
		return ResponseEntity.status(HttpStatus.OK).body(deleted_credential);
	}
}
