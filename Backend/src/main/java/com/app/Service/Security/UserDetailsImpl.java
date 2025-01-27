//package com.app.Service.Security;
//
//
//import com.app.Models.Entities.Credentials;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class UserDetailsImpl implements UserDetails {
//
//    private String username;
//    private String password;
//    private List<GrantedAuthority> authorities;
//
//    public UserDetailsImpl(Credentials credentials){
//        this.username = credentials.getName();
//        this.password = credentials.getPassword();
//        this.authorities = Arrays
//                                .stream(credentials.getRoles()
//                                            .split(","))
//                                .map(SimpleGrantedAuthority::new)
//                                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
//    }
//}
