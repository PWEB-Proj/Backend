package com.example.pweb.controller;

import com.example.pweb.persistance.models.Category;
import com.example.pweb.service.CategoryService;
import com.example.pweb.service.UserService;
import com.example.pweb.utils.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/categories")
public class CategoryController {

    private final UserService userService;

    @PutMapping("/{id}")
    public void updateCategories(@PathVariable("id") Integer id, @RequestBody Set<CategoryId> preferences) {
        userService.updatePreferences(id, preferences);
    }
}

