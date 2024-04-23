package com.example.pweb.service;

import com.example.pweb.dto.ItineraryDTO;
import com.example.pweb.dto.PlaceDTO;
import com.example.pweb.exceptions.NearbyTomTomException;
import com.example.pweb.mappers.factory.PlaceFactory;
import com.example.pweb.mappers.factory.PreferenceFactory;
import com.example.pweb.persistance.models.Category;
import com.example.pweb.persistance.models.Itinerary;
import com.example.pweb.persistance.models.Place;
import com.example.pweb.persistance.repositories.PlacesRepository;
import com.example.pweb.utils.GeoBias;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService{

    private final PreferenceFactory preferenceFactory;

    private final PlacesRepository placesRepository;

    private final PlaceFactory placeFactory;

    private final WebClient webClient;

    private final CategoryService categoryService;

    @Override
    public Mono<PlaceDTO> getRecommendations(Integer id, GeoBias geoBias) {
        String categories = preferenceFactory.getPreferencesFromList(categoryService.getPreferencesById(id));
        return getAllByCategory(categories, geoBias);
    }

    @Override
    public void savePlacesFromItinerary(List<PlaceDTO> placesDTO, Itinerary itinerary) {
        List<Place> places = placeFactory.getPlacesFromPlacesDTO(placesDTO, itinerary);
        placesRepository.saveAll(places);
    }

    @Override
    public List<Place> getPlacesByItinerary(Itinerary itinerary) {
        return placesRepository.findByItineraryId(itinerary);
    }

    @Override
    public void deletePlacesByItinerary(Itinerary itinerary) {
        placesRepository.deletePlacesByItineraryId(itinerary);
    }

    public Mono<PlaceDTO> getAllByCategory(String category, GeoBias geoBias) {
        String tomtomKey = "D83lFXeuwBypQmOtG55u8j4yOV18e1AV";
        String url = String.format("https://api.tomtom.com/search/2/nearbySearch/.json?key=%s&lat=%s&lon=%s&categorySet=%s", tomtomKey, geoBias.getLat(), geoBias.getLon(), category);
        return webClient.method(HttpMethod.GET)
                .uri(url)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(), clientResponse -> clientResponse.bodyToMono(String.class).flatMap(error ->
                        Mono.error(new NearbyTomTomException("Error with TomTom Nearby API: " + error))
                ))
                .bodyToMono(PlaceDTO.class);
    }
}
