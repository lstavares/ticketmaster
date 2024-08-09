package com.mercadolivre.ticketmaster.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Severity {

    ISSUE_HIGH(1, "Issue high"),
    HIGH(2, "High"),
    MEDIUM(3, "Medium"),
    LOW(4, "Low");

    @JsonValue
    private final Integer code;

    private final String description;

    @JsonCreator
    public static Severity fromValue(Integer value) {
        for (Severity type : Severity.values()) {
            if (type.code.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown severity type " + value);
    }

}
