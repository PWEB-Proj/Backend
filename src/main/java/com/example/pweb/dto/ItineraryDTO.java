package com.example.pweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryDTO {

    @JsonProperty("days")
    private Integer noDays;

    @JsonProperty("location")
    private String locationName;

    @JsonProperty("places")
    private List<PlaceDTO> places;

}
