package com.example.pweb.service;

import com.example.pweb.exceptions.UserNotFoundException;
import com.example.pweb.mappers.factory.UserFactory;
import com.example.pweb.persistance.repositories.OurUsersRepository;
import com.example.pweb.utils.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
