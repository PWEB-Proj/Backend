package com.example.pweb.dto;

import com.example.pweb.utils.Address;
import com.example.pweb.utils.EntryPoint;
import com.example.pweb.utils.GeoBias;
import com.example.pweb.utils.POI;
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
public class PlaceDTO {

    @JsonProperty("type")
    private String type;

    @JsonProperty("id")
    private String id;

    @JsonProperty("score")
    private double score;

    @JsonProperty("dist")
    private double dist;

    @JsonProperty("poi")
    private POI poi;

    @JsonProperty("address")
    private Address address;

    @JsonProperty("position")
    private GeoBias position;

    @JsonProperty("entryPoints")
    private List<EntryPoint> entryPoints;
}
