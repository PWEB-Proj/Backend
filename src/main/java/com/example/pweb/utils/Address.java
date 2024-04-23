package com.example.pweb.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @JsonProperty("streetNumber")
    private String streetNumber;

    @JsonProperty("streetName")
    private String streetName;

    @JsonProperty("municipality")
    private String municipality;

    @JsonProperty("countrySubdivision")
    private String countrySubdivision;

    @JsonProperty("country")
    private String country;

    @JsonProperty("postalCode")
    private String postalCode;

    @JsonProperty("countryCode")
    private String countryCode;

    @JsonProperty("freeformAddress")
    private String freeformAddress;
}
