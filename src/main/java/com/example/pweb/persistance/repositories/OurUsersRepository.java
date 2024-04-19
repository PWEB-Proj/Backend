package com.example.pweb.persistance.repositories;

import com.example.pweb.persistance.models.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OurUsersRepository extends JpaRepository<OurUsers, Integer> {
    Optional<OurUsers> findByEmail(String username);
}
