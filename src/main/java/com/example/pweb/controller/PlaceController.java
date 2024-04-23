package com.example.pweb.controller;

import com.example.pweb.dto.PlaceDTO;
import com.example.pweb.service.PlaceService;
import com.example.pweb.utils.GeoBias;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/{id}/nearby")
    public ResponseEntity<Mono<PlaceDTO>> getRecommendations(@PathVariable("id") Integer id, @RequestParam("lat") double lat, @RequestParam("lon") double lon) {
        return ResponseEntity.ok(placeService.getRecommendations(id, new GeoBias(lat, lon)));
    }
}
