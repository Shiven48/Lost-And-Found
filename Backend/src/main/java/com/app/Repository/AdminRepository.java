package com.app.Repository;

import com.app.Models.Entities.Admin;
import com.app.Models.Interface.BaseAuditInterface;
import com.app.Models.Interface.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
