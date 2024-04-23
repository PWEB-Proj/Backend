package com.example.pweb.service;

import com.example.pweb.dto.ItineraryDTO;
import com.example.pweb.dto.RetrieveItineraryDTO;

public interface ItineraryService {

    Integer createItinerary(ItineraryDTO itineraryDTO, Integer userId);

    RetrieveItineraryDTO getItinerary(Integer id);

    void deleteItinerary(Integer id);
}
