package com.app.Models.Interface;

import com.app.Models.Entities.Credentials;
import com.app.Models.Entities.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.List;

public interface UserType extends BaseAuditInterface {

   Credentials getCredentials();

   void setCredentials(Credentials credentials);

   Boolean getLoggedIn();

   void setLoggedIn(Boolean loggedIn);

   Long getId();

   void setId(Long id);

}
