package com.mercadolivre.ticketmaster.domain;


import com.mercadolivre.ticketmaster.domain.enums.Severity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDateTime createdAt;

    @Transient
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Severity severity;
}
