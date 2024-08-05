package com.mercadolivre.ticketmaster.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Severity {

    ISSUE_HIGH(1, "Issue high"),
    HIGH(2, "High"),
    MEDIUM(3, "Medium"),
    LOW(4, "Low");

    private final int code;
    private final String description;

}
