package com.example.pweb.mappers.translator;

import com.example.pweb.dto.RetrieveItineraryDTO;
import com.example.pweb.persistance.models.Itinerary;
import com.example.pweb.persistance.models.Place;

import java.util.List;

public interface ItineraryTranslator {

    RetrieveItineraryDTO translateToRetrievePlaceDTO(List<Place> places, Itinerary itinerary);
}
