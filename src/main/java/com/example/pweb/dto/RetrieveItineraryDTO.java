package com.example.pweb.dto;

import com.example.pweb.persistance.models.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveItineraryDTO {

    private Integer id;

    private Integer noDays;

    private String locationName;

    private List<Place> places;
}
