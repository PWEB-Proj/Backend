package com.example.pweb.mappers.factory;

import com.example.pweb.dto.ItineraryDTO;
import com.example.pweb.persistance.models.Itinerary;
import com.example.pweb.persistance.models.OurUsers;

public interface ItineraryFactory {

    Itinerary getItineraryFromItineraryDTO(ItineraryDTO itineraryDTO, OurUsers user);
}
