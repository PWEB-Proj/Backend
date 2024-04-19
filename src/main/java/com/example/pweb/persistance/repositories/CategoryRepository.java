package com.example.pweb.persistance.repositories;

import com.example.pweb.persistance.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE c.id IN :ids")
    List<Category> findAllCategoriesById(List<Integer> ids);

}
