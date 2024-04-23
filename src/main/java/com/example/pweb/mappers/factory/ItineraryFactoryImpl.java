package com.example.pweb.mappers.factory;

import com.example.pweb.dto.ItineraryDTO;
import com.example.pweb.persistance.models.Itinerary;
import com.example.pweb.persistance.models.OurUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItineraryFactoryImpl implements ItineraryFactory {
    @Override
    public Itinerary getItineraryFromItineraryDTO(ItineraryDTO itineraryDTO, OurUsers user) {
        Itinerary itinerary = new Itinerary();
        itinerary.setNoDays(itineraryDTO.getNoDays());
        itinerary.setOwner(user);
        itinerary.setLocationName(itineraryDTO.getLocationName());
        return itinerary;
    }
}
