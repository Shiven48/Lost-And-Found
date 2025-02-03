package com.app.Service.Security;

import com.app.Models.Entities.Credentials;
import com.app.Repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CredentialsRepository repo;
    private PasswordEncoder encoder;

    public UserDetailsServiceImpl(PasswordEncoder encoder){
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
         Optional<Credentials> credentials = repo.findByName((username));
        System.out.println(credentials);
        return credentials.map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found"));
    }
}
