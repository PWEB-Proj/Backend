package com.example.pweb.service;

import com.example.pweb.utils.CategoryId;

import java.util.Set;

public interface UserService {

    void updatePreferences(Integer id, Set<CategoryId> categories);
}
