package com.app.Controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Entity.Credentials;
import com.app.Service.CredentialService;


// This is for credentials
@RequestMapping("api/")
@RestController
public class CredentialsController {

	private CredentialService credentialService;
	
	public CredentialsController(CredentialService credentialService) {
		this.credentialService = credentialService;
	}
	
	// sign in
	@PostMapping(path="credentials/credential",name = "PostCredentials",consumes = {"application/json","application/xml"})
	public ResponseEntity<Credentials> postCredentials(@RequestBody Credentials credential, HttpSession session) {
		Credentials response_credential = credentialService.addCredentials(credential);
		session.setAttribute("credential", response_credential);
		return ResponseEntity.status(HttpStatus.CREATED).body(response_credential);
	}

	// log in
	@GetMapping(path="credentials/credential/{id}",name = "GetCredentials")
	public ResponseEntity<Credentials> GetCredentialsById(@PathVariable("id") Long id) {
		Credentials response_AllCredential = credentialService.getCredentialsById(id);
		return ResponseEntity.status(HttpStatus.OK).body(response_AllCredential);
	}

	// get all credentials
	@GetMapping(path="credentials/credential",name = "GetCredentials")
	public ResponseEntity<List<Credentials>> GetCredentials() {
		List<Credentials> response_AllCredential = credentialService.getAllCredentials();
		return ResponseEntity.status(HttpStatus.OK).body(response_AllCredential);
	}
}
