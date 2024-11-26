package com.app.Repository;

import com.app.Entity.Item;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {

    @EntityGraph(attributePaths = {"finder", "owner"})
    Optional<Item> findWithRelationshipsById(Long id);

}
