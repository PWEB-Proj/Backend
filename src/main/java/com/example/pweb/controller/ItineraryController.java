package com.example.pweb.controller;

import com.example.pweb.dto.ItineraryDTO;
import com.example.pweb.dto.RetrieveItineraryDTO;
import com.example.pweb.service.ItineraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/itinerary")
public class ItineraryController {

    private final ItineraryService itineraryService;

    @PostMapping("/{id}")
    public ResponseEntity<Integer> createItinerary(@PathVariable("id") Integer id, @RequestBody ItineraryDTO itineraryDTO) {
        return ResponseEntity.ok(itineraryService.createItinerary(itineraryDTO, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RetrieveItineraryDTO> getItinerary(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(itineraryService.getItinerary(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteItinerary(@PathVariable("id") Integer id) {
        itineraryService.deleteItinerary(id);
        return ResponseEntity.ok().build();
    }

}
