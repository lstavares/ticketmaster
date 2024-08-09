package com.mercadolivre.ticketmaster.integration;

import com.mercadolivre.ticketmaster.application.service.TicketService;
import com.mercadolivre.ticketmaster.domain.dto.TicketDTO;
import com.mercadolivre.ticketmaster.domain.exception.TicketNotFoundException;
import com.mercadolivre.ticketmaster.infrastructure.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.ANY;

@SpringBootTest
@AutoConfigureTestDatabase(replace = ANY)
@Transactional
@ActiveProfiles("test")
public class TicketServiceIntegrationTest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void testCreateAndRetrieveTicket() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTitle("New Ticket");
        ticketDTO.setDescription("New Description");

        TicketDTO createdTicket = ticketService.save(ticketDTO);
        TicketDTO retrievedTicket = ticketService.get(createdTicket.getId());

        assertEquals(createdTicket.getTitle(), retrievedTicket.getTitle());
        assertEquals(createdTicket.getDescription(), retrievedTicket.getDescription());
    }

    @Test
    public void testUpdateTicket() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTitle("New Ticket");
        ticketDTO.setDescription("New Description");

        TicketDTO createdTicket = ticketService.save(ticketDTO);
        createdTicket.setTitle("Updated Ticket");

        TicketDTO updatedTicket = ticketService.update(createdTicket.getId(), createdTicket);
        assertEquals("Updated Ticket", updatedTicket.getTitle());
    }

    @Test
    public void testDeleteTicket() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTitle("New Ticket");
        ticketDTO.setDescription("New Description");

        TicketDTO createdTicket = ticketService.save(ticketDTO);
        ticketService.delete(createdTicket.getId());

        assertThrows(TicketNotFoundException.class, () -> ticketService.get(createdTicket.getId()));
    }

    @Test
    public void testRetrieveNonExistentTicket() {
        assertThrows(TicketNotFoundException.class, () -> ticketService.get(999L));
    }
}
