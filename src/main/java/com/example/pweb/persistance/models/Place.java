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

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lon")
    private double lon;

    @Column(name = "visited")
    private boolean visited = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "itinerary_id_id")
    private Itinerary itineraryId;
}
