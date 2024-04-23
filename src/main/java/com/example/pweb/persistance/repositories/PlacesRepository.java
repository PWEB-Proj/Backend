package com.example.pweb.persistance.repositories;

import com.example.pweb.persistance.models.Itinerary;
import com.example.pweb.persistance.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlacesRepository extends JpaRepository<Place, Integer> {

    List<Place> findByItineraryId(Itinerary itinerary);

    void deletePlacesByItineraryId(Itinerary itinerary);
}
