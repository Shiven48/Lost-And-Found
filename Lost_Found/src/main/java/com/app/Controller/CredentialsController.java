package com.app.Controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
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
	public ResponseEntity<Credentials> postCredentials(@RequestBody Credentials credential) {
		Credentials response_credential = credentialService.addCredentials(credential);
		return ResponseEntity.status(HttpStatus.CREATED).body(response_credential);
	}

	// log in (No security added yet)
	@GetMapping(path="credentials/{id}",name = "GetCredentials")
	public ResponseEntity<Credentials> GetCredentialsById(@PathVariable("id") Long id) {
		Credentials response_AllCredential = credentialService.getCredentialsById(id);
		return ResponseEntity.status(HttpStatus.OK).body(response_AllCredential);
	}

	// get all credentials
	@GetMapping(path="credentials",name = "GetCredentials")
	public ResponseEntity<List<Credentials>> GetCredentials() {
		List<Credentials> response_AllCredential = credentialService.getAllCredentials();
		return ResponseEntity.status(HttpStatus.OK).body(response_AllCredential);
	}

	// Endpoint to update existing credential
	@PutMapping(name="updateCredential", path="credentials/{id}")
	public ResponseEntity<Credentials> updateCredentials(@PathVariable("id") Long id, @RequestBody Credentials credential){
		Credentials updated_credential = credentialService.updateCredentialsService(id,credential);
		return ResponseEntity.status(HttpStatus.OK).body(updated_credential);
	}

	// Endpoint to delete a credential
	@DeleteMapping(name="deleteCredential",path="credentials/{id}")
	public ResponseEntity<Credentials> deleteCredentials(@PathVariable("id") Long id) {
		Credentials deleted_credential = credentialService.deleteCredentialById(id);
		return ResponseEntity.status(HttpStatus.OK).body(deleted_credential);
	}
}
