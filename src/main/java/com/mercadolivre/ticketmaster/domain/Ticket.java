package com.mercadolivre.ticketmaster.domain;


import com.mercadolivre.ticketmaster.domain.enums.Severity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ticket {

    private String title;
    private String description;
    private Severity severity;
    private String category;
    private String subcategory;
}
