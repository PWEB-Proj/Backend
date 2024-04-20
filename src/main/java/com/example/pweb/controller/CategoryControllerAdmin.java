package com.example.pweb.controller;

import com.example.pweb.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoryControllerAdmin {

    private final CategoryService categoryService;

    @PostMapping
    public void updateCategories() {
        categoryService.updateCategories();
    }
}