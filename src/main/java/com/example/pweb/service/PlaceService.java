package com.example.pweb.service;

import com.example.pweb.dto.ItineraryDTO;
import com.example.pweb.dto.PlaceDTO;
import com.example.pweb.persistance.models.Category;
import com.example.pweb.persistance.models.Itinerary;
import com.example.pweb.persistance.models.Place;
import com.example.pweb.utils.GeoBias;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PlaceService {

    Mono<PlaceDTO> getRecommendations(Integer id, GeoBias geoBias);

    void savePlacesFromItinerary(List<PlaceDTO> places, Itinerary itinerary);

    List<Place> getPlacesByItinerary(Itinerary itinerary);

    void deletePlacesByItinerary(Itinerary itinerary);
}
