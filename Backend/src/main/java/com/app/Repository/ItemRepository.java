package com.app.Repository;

import com.app.Entity.Item;
import com.app.Entity.Lost_Found;
import jakarta.persistence.NamedNativeQueries;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long>, JpaSpecificationExecutor<Item> {

    // Here used JPQL for the simple queries
    // Note :- Use Specification for more dynamic and complex query

    @Query("SELECT i FROM Item i WHERE i.lost_found = :lostFound ORDER BY i.time ASC")
    List<Item> findAllByLost_FoundOrderByTimeAsc(@Param("lostFound") Lost_Found lost_found);

    @Query("SELECT i FROM Item i WHERE i.lost_found = :lostFound Order By i.time DESC")
    List<Item> findAllByLost_FoundOrderByTimeDesc(@Param("lostFound") Lost_Found lost_found);

    @Query("SELECT i FROM Item i ORDER BY i.time ASC")
    List<Item> findAllOrderByTimeAsc();

    @Query("SELECT i FROM Item i ORDER BY i.time DESC")
    List<Item> findAllOrderByTimeDesc();

    @Query("SELECT i FROM Item i WHERE upper(i.category) = upper(:category)")
    Optional<List<Item>> findAllByCategory(@Param("category") String category);
}
