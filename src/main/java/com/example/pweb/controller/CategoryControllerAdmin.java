package com.example.pweb.controller;

import com.example.pweb.persistance.models.Category;
import com.example.pweb.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoryControllerAdmin {

    private final CategoryService categoryService;

    @PostMapping
    public void updateCategories() {
        categoryService.updateCategories();
    }

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}