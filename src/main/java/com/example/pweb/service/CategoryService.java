package com.example.pweb.service;

import com.example.pweb.persistance.models.Category;

import java.util.List;

public interface CategoryService {
    void updateCategories();

    List<Category> getAllCategories();

    List<Category> getPreferencesById(Integer id);

    void checkIfCategoryExists(String category);
}
