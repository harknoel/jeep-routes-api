package com.jeep.code.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue
    private Integer placeId;

    private String placeName;

    @ManyToOne
    @JoinColumn(name = "jeep_id")
    private JeepCode jeepCode;
}
