package com.example.pweb.service;

import com.example.pweb.dto.ItineraryDTO;
import com.example.pweb.dto.RetrieveItineraryDTO;
import com.example.pweb.mappers.factory.ItineraryFactory;
import com.example.pweb.mappers.translator.ItineraryTranslator;
import com.example.pweb.persistance.models.Itinerary;
import com.example.pweb.persistance.models.OurUsers;
import com.example.pweb.persistance.models.Place;
import com.example.pweb.persistance.repositories.ItineraryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItineraryServiceImpl implements ItineraryService{

    private final PlaceService placeService;

    private final ItineraryRepository itineraryRepository;

    private final ItineraryFactory itineraryFactory;

    private final UserService userService;

    private final ItineraryTranslator itineraryTranslator;

    @Override
    public Integer createItinerary(ItineraryDTO itineraryDTO, Integer userId) {
        OurUsers user = userService.getUserById(userId);
        if(user == null) {
            throw new RuntimeException("User not found!");
        }
        try {
            Itinerary itinerary = itineraryFactory.getItineraryFromItineraryDTO(itineraryDTO, user);
            Itinerary itineraryResult = itineraryRepository.save(itinerary);
            placeService.savePlacesFromItinerary(itineraryDTO.getPlaces(), itineraryResult);
            return itineraryResult.getId();
        } catch (Exception e) {
            throw new RuntimeException("Error saving itinerary!: " + e.getMessage());
        }
    }

    @Override
    public RetrieveItineraryDTO getItinerary(Integer id) {
        Itinerary itinerary = itineraryRepository.findById(id).orElse(null);
        if(itinerary == null) {
            throw new RuntimeException("Itinerary not found!");
        }
        List<Place> places = placeService.getPlacesByItinerary(itinerary);
        if (places.isEmpty()) {
            throw new RuntimeException("Places not found!");
        }
        return itineraryTranslator.translateToRetrievePlaceDTO(places, itinerary);
    }

    @Override
    @Transactional
    public void deleteItinerary(Integer id) {
        Itinerary itinerary = itineraryRepository.findById(id).orElse(null);
        if(itinerary == null) {
            throw new RuntimeException("Itinerary not found!");
        }
        placeService.deletePlacesByItinerary(itinerary);
        itineraryRepository.delete(itinerary);
    }
}
