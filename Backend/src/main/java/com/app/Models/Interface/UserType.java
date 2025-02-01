package com.app.Models.Interface;

import com.app.Models.Entities.Credentials;

public interface UserType extends BaseAuditInterface {

   Credentials getCredentials();

   void setCredentials(Credentials credentials);

   Boolean getLoggedIn();

   void setLoggedIn(Boolean loggedIn);

   Long getId();

   void setId(Long id);

}
