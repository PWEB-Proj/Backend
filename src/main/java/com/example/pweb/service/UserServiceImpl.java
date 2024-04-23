package com.example.pweb.service;

import com.example.pweb.exceptions.UserNotFoundException;
import com.example.pweb.mappers.factory.UserFactory;
import com.example.pweb.persistance.models.Category;
import com.example.pweb.persistance.models.OurUsers;
import com.example.pweb.persistance.repositories.OurUsersRepository;
import com.example.pweb.utils.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserFactory userFactory;

    private final OurUsersRepository userRepository;

    @Override
    public void updatePreferences(Integer id, Set<CategoryId> categories) {
        userRepository.findById(id).map(user -> {
            user.setPreferences(userFactory.getCategoriesFromPreferences(categories));
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    @Override
    public List<Category> getPreferencesById(Integer id) {
        return userRepository.findById(id)
                .map(OurUsers::getPreferences)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    @Override
    public OurUsers getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }
}
