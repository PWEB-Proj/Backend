package com.example.pweb.mappers.factory;

import com.example.pweb.dto.PlaceDTO;
import com.example.pweb.persistance.models.Itinerary;
import com.example.pweb.persistance.models.Place;

import java.util.List;

public interface PlaceFactory {

    List<Place> getPlacesFromPlacesDTO(List<PlaceDTO> places, Itinerary itinerary);
}
