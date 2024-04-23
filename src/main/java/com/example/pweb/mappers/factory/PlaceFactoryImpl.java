package com.example.pweb.mappers.factory;

import com.example.pweb.dto.PlaceDTO;
import com.example.pweb.persistance.models.Itinerary;
import com.example.pweb.persistance.models.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlaceFactoryImpl implements PlaceFactory{
    @Override
    public List<Place> getPlacesFromPlacesDTO(List<PlaceDTO> places, Itinerary itinerary) {
        return places.stream().map(placeDTO -> {
            Place place = new Place();
            place.setName(placeDTO.getPoi().getName());
            place.setAddress(placeDTO.getAddress().getFreeformAddress());
            place.setLat(placeDTO.getPosition().getLat());
            place.setLon(placeDTO.getPosition().getLon());
            place.setPhone(placeDTO.getPoi().getPhone());
            place.setType(placeDTO.getType());
            place.setItineraryId(itinerary);
            return place;
        }).toList();
    }
}
