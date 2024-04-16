package com.jeep.code.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jeep_code")
public class JeepCode {
    @Id
    @GeneratedValue
    private Integer jeepId;

    private String code;

    @OneToMany(mappedBy = "jeepCode")
    List<Place> places;
}
