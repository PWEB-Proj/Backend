package com.example.pweb.mappers.translator;

import com.example.pweb.dto.RetrieveItineraryDTO;
import com.example.pweb.persistance.models.Itinerary;
import com.example.pweb.persistance.models.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItineraryTranslatorImpl implements ItineraryTranslator {

    @Override
    public RetrieveItineraryDTO translateToRetrievePlaceDTO(List<Place> places, Itinerary itinerary) {
        RetrieveItineraryDTO retrieveItineraryDTO = new RetrieveItineraryDTO();
        retrieveItineraryDTO.setId(itinerary.getId());
        retrieveItineraryDTO.setNoDays(itinerary.getNoDays());
        retrieveItineraryDTO.setLocationName(itinerary.getLocationName());
        retrieveItineraryDTO.setPlaces(places);
        return retrieveItineraryDTO;
    }
}
