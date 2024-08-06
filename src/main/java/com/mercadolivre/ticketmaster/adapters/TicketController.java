package com.mercadolivre.ticketmaster.adapters;


import com.mercadolivre.ticketmaster.application.service.CategoryService;
import com.mercadolivre.ticketmaster.application.service.TicketService;
import com.mercadolivre.ticketmaster.infrastructure.client.users.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> create(HttpRequest httpRequest) {
        return created(httpRequest.getURI()).body("Ticket create method");
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ok(ticketService.list());
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<?> get(@PathVariable Long ticketId) {
        UserResponse currentUser = ticketService.getCurrentUser();
        return ok(ticketService.get(ticketId));
    }

    @GetMapping("/{ticketId}/categories")
    public ResponseEntity<?> categories(@PathVariable String ticketId) {
        return ok(categoryService.list());
    }

    @PatchMapping
    public ResponseEntity<?> update() {
        return ok("Ticket update method");
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<?> delete(@PathVariable String ticketId) {
        return noContent().build();
    }
}
