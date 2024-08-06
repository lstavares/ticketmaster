package com.mercadolivre.ticketmaster.adapters;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/subcategories")
@RequiredArgsConstructor
public class SubcategoryController {

    @PostMapping
    public ResponseEntity<?> create(HttpRequest httpRequest) {
        return created(httpRequest.getURI()).body("Subcategory create method");
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ok("Subcategory list method");
    }

    @GetMapping("/subcategoryId")
    public ResponseEntity<?> get(String subcategoryId) {
        return ok("Subcategory get method");
    }

    @PatchMapping
    public ResponseEntity<?> update() {
        return ok("Subcategory update method");
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        return noContent().build();
    }
}
