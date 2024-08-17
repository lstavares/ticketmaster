package com.mercadolivre.ticketmaster.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Severity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String level;

    @OneToMany(mappedBy = "severity", cascade = ALL)
    @JsonBackReference
    private List<Ticket> tickets;

}

