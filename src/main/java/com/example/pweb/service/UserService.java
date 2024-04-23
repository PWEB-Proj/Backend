package com.example.pweb.service;

import com.example.pweb.persistance.models.Category;
import com.example.pweb.persistance.models.OurUsers;
import com.example.pweb.utils.CategoryId;

import java.util.List;
import java.util.Set;

public interface UserService {

    void updatePreferences(Integer id, Set<CategoryId> categories);

    List<Category> getPreferencesById(Integer id);

    OurUsers getUserById(Integer id);
}
