package com.app.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Entity.Credentials;
import com.app.Service.CredentialService;

@RequestMapping("api/")
@RestController
public class CredentialsController {

	private CredentialService credentialService;
	
	public CredentialsController(CredentialService credentialService) {
		this.credentialService = credentialService;
	}
	
	// sign up
	@PostMapping(path="credentials/credential",name = "PostCredentials",consumes = {"application/json","application/xml"})
	public ResponseEntity<Credentials> postCredentials(@RequestBody Credentials credential) {
		Credentials response_credential = credentialService.addCredentials(credential);
		return ResponseEntity.status(HttpStatus.CREATED).body(response_credential);
	}
}
