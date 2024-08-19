package com.mercadolivre.ticketmaster.application.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static org.slf4j.MDC.put;


public class Helper {

    public static class LogHelper {
        private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        public static void putObjectInMDC(String key, Object value) {
            try {
                String jsonValue = objectMapper.writeValueAsString(value).replace("\n", "").replace("\r", "").replace("\t", "");
                put(key, jsonValue);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
