package com.mercadolivre.ticketmaster.adapters;

import com.mercadolivre.ticketmaster.application.service.TicketService;
import com.mercadolivre.ticketmaster.domain.dto.TicketDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class TicketControllerTest {

    @InjectMocks
    private TicketController ticketController;

    @Mock
    private TicketService ticketService;

    @Mock
    private HttpServletRequest httpRequest;

    @Test
    void testCreate() {
        TicketDTO ticketDTO = new TicketDTO();
        when(ticketService.save(any(TicketDTO.class))).thenReturn(ticketDTO);
        when(httpRequest.getRequestURI()).thenReturn("/tickets");

        ResponseEntity<TicketDTO> response = ticketController.create(ticketDTO, httpRequest);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(ticketDTO, response.getBody());
    }

    @Test
    void testList() {
        TicketDTO ticketDTO = new TicketDTO();
        when(ticketService.list()).thenReturn(Collections.singletonList(ticketDTO));

        ResponseEntity<List<TicketDTO>> response = ticketController.list();

        assertEquals(OK, response.getStatusCode());
        assertEquals(1, requireNonNull(response.getBody()).size());
        assertEquals(ticketDTO, response.getBody().get(0));
    }

    @Test
    void testGet() {
        Long ticketId = 1L;
        TicketDTO ticketDTO = new TicketDTO();
        when(ticketService.get(ticketId)).thenReturn(ticketDTO);

        ResponseEntity<TicketDTO> response = ticketController.get(ticketId);

        assertEquals(OK, response.getStatusCode());
        assertEquals(ticketDTO, response.getBody());
    }

    @Test
    void testUpdate() {
        Long ticketId = 1L;
        TicketDTO ticketDTO = new TicketDTO();
        when(ticketService.update(ticketId, ticketDTO)).thenReturn(ticketDTO);

        ResponseEntity<TicketDTO> response = ticketController.update(ticketId, ticketDTO);

        assertEquals(OK, response.getStatusCode());
        assertEquals(ticketDTO, response.getBody());
    }

    @Test
    void testDelete() {
        Long ticketId = 1L;

        ResponseEntity<Void> response = ticketController.delete(ticketId);

        assertEquals(NO_CONTENT, response.getStatusCode());
        verify(ticketService, times(1)).delete(ticketId);
    }
}
