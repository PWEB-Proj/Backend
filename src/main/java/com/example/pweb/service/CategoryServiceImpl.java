package com.example.pweb.service;

import com.example.pweb.dto.CategoryDTO;
import com.example.pweb.exceptions.CategoryTomTomException;
import com.example.pweb.persistance.models.Category;
import com.example.pweb.persistance.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final WebClient webClient;

    @Override
    public void updateCategories() {
        String tomtomKey = "D83lFXeuwBypQmOtG55u8j4yOV18e1AV";
        String url = String.format("https://api.tomtom.com/search/2/poiCategories.json?key=%s", tomtomKey);
        webClient.method(HttpMethod.GET)
                .uri(url)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(), clientResponse -> clientResponse.bodyToMono(String.class).flatMap(error ->
                        Mono.error(new CategoryTomTomException("Error with TomTom Categories API: " + error))
                ))
                .bodyToMono(CategoryDTO.class).subscribe(categoryDTO -> {
                    List<Category> newList = categoryDTO.getCategories().stream().filter(category -> category.getId().toString().length() == 4).toList();
                    categoryRepository.saveAll(newList);
                });
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void checkIfCategoryExists(String category) {
        if(!categoryRepository.existsById(Integer.valueOf(category))){
            throw new CategoryTomTomException("Category does not exist!");
        }
    }
}
