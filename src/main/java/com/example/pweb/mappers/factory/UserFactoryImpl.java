package com.example.pweb.mappers.factory;

import com.example.pweb.persistance.models.Category;
import com.example.pweb.persistance.repositories.CategoryRepository;
import com.example.pweb.utils.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserFactoryImpl implements UserFactory{

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategoriesFromPreferences(Set<CategoryId> preferences) {
        return categoryRepository.findByIdIn(preferences.stream().map(CategoryId::getId).toList());
    }
}
