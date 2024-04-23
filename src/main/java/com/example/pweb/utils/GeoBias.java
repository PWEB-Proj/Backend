package com.example.pweb.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeoBias {

    @JsonProperty("lat")
    private double lat;

    @JsonProperty("lon")
    private double lon;
}
