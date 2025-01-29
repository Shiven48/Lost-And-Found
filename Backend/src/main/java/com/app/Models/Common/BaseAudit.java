package com.app.Models.Common;

import com.app.Models.Interface.BaseAuditInterface;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseAudit extends BaseEntity implements Serializable, BaseAuditInterface {

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    protected LocalDateTime registrationDate;

    @UpdateTimestamp
    @Column(nullable = false)
    protected LocalDateTime lastModified;

    public BaseAudit(Long id) {
        super(id);
    }

    public BaseAudit(){
        super();
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof BaseAudit that)) return false;
        if(!super.equals(o)) return false;

        return registrationDate.equals(that.registrationDate) &&
               lastModified.equals(that.lastModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), registrationDate, lastModified);
    }

    @Override
    public String toString() {
        return "BaseAudit{" +
                "registrationDate=" + registrationDate +
                ", lastModified=" + lastModified +
                super.toString()+
                '}';
    }
}
