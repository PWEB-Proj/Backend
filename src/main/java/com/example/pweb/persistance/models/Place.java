package com.example.pweb.persistance.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;

    private String name;

    private String phone;

    private String address;

    private double lat;

    private double lon;

    private boolean visited = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Itinerary itineraryId;
}
