package com.app.Models.Common;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="id_seq",sequenceName = "id_seq",initialValue = 100,allocationSize = 5)
    protected Long id;

    public BaseEntity(Long id) {}

    public BaseEntity() { }

    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof BaseEntity that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BaseEntity {" +
                "id = " + id +
                "}";
    }
}
