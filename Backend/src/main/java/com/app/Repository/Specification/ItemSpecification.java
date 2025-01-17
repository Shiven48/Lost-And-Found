package com.app.Repository.Specification;

import com.app.Entity.Item;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ItemSpecification {

    public static Specification<Item> hasCategory(String category) {
       return (root,query,cb) -> {
            if(category==null || category.isEmpty()){
                System.out.println("Category is null or empty");
                return cb.conjunction();
            }
            return cb.like(
                    cb.lower(root.get("category")),
                    "%"+category.toLowerCase()+"%"
            );
        };
    }

    public static Specification<Item> hasName(String name) {
        return (root,query,cb) -> {
            if(name == null || name.isEmpty()){
                System.out.println("Name is null or empty");
                return cb.conjunction();
            }
            return cb.like(
                    cb.lower(root.get("name")),
                    "%"+name.toLowerCase()+"%"
            );
        };
    }

}
