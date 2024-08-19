package com.mercadolivre.ticketmaster.adapters;


import com.mercadolivre.ticketmaster.application.service.TicketService;
import com.mercadolivre.ticketmaster.domain.dto.TicketDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/tickets")
@Validated
@RequiredArgsConstructor
public class TicketController {

    private final Logger logger = getLogger(this.getClass());
    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketDTO> create(@Valid @RequestBody TicketDTO ticket, HttpServletRequest httpRequest) {
        logger.info("Received request for creating ticket: {}", ticket);
        return created(URI.create(httpRequest.getRequestURI())).body(ticketService.save(ticket));
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> list() {
        logger.info("Received request for listing tickets");
        return ok(ticketService.list());
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDTO> get(@PathVariable Long ticketId) {
        logger.info("Received request for getting ticket: {}", ticketId);
        return ok(ticketService.get(ticketId));
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<TicketDTO> update(@PathVariable Long ticketId, @Valid @RequestBody TicketDTO ticketDTO) {
        logger.info("Received request for updating ticket: {}", ticketId);
        return ok(ticketService.update(ticketId, ticketDTO));
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> delete(@PathVariable Long ticketId) {
        ticketService.delete(ticketId);
        return noContent().build();
    }
}
