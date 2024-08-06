package com.mercadolivre.ticketmaster.domain;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String username;
    private String email;
}
