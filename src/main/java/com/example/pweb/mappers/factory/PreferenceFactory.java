package com.example.pweb.mappers.factory;

import com.example.pweb.persistance.models.Category;

import java.util.List;

public interface PreferenceFactory {

    String getPreferencesFromList(List<Category> preferences);
}