package com.example.pweb.mappers.factory;

import com.example.pweb.persistance.models.Category;
import com.example.pweb.utils.CategoryId;

import java.util.List;
import java.util.Set;

public interface UserFactory {

    List<Category> getCategoriesFromPreferences(Set<CategoryId> preferences);
}
