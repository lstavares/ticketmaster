package com.mercadolivre.ticketmaster.application.service;


import com.mercadolivre.ticketmaster.domain.dto.TicketDTO;
import com.mercadolivre.ticketmaster.domain.entity.Ticket;
import com.mercadolivre.ticketmaster.domain.exception.TicketNotFoundException;
import com.mercadolivre.ticketmaster.domain.mapper.TicketMapper;
import com.mercadolivre.ticketmaster.infrastructure.client.users.UsersClient;
import com.mercadolivre.ticketmaster.infrastructure.client.users.response.UserResponse;
import com.mercadolivre.ticketmaster.infrastructure.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private UsersClient usersClient;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private TicketMapper ticketMapper;

    @Test
    void testList() {
        Ticket ticket = new Ticket();
        TicketDTO ticketDTO = new TicketDTO();
        when(ticketRepository.findAll()).thenReturn(Collections.singletonList(ticket));
        when(ticketMapper.toDto(ticket)).thenReturn(ticketDTO);

        var result = ticketService.list();

        assertEquals(1, result.size());
        assertEquals(ticketDTO, result.get(0));
    }

    @Test
    void testGet() {
        Long ticketId = 1L;
        Ticket ticket = new Ticket();
        TicketDTO ticketDTO = new TicketDTO();
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(ticketMapper.toDto(ticket)).thenReturn(ticketDTO);

        var result = ticketService.get(ticketId);

        assertEquals(ticketDTO, result);
    }

    @Test
    void testGetNotFound() {
        Long ticketId = 1L;
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.empty());

        assertThrows(TicketNotFoundException.class, () -> ticketService.get(ticketId));
    }

    @Test
    void testSave() {
        TicketDTO ticketDTO = new TicketDTO();
        Ticket ticket = new Ticket();
        when(ticketMapper.toEntity(ticketDTO)).thenReturn(ticket);
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        when(ticketMapper.toDto(ticket)).thenReturn(ticketDTO);
        when(usersClient.getUser(anyLong())).thenReturn(UserResponse.builder().build());

        var result = ticketService.save(ticketDTO);

        assertEquals(ticketDTO, result);
    }

    @Test
    void testUpdate() {
        Long ticketId = 1L;
        TicketDTO ticketDTO = new TicketDTO();
        Ticket ticket = new Ticket();
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        when(ticketMapper.toDto(ticket)).thenReturn(ticketDTO);

        var result = ticketService.update(ticketId, ticketDTO);

        assertEquals(ticketDTO, result);
    }

    @Test
    void testDelete() {
        Long ticketId = 1L;
        doNothing().when(ticketRepository).deleteById(ticketId);

        ticketService.delete(ticketId);

        verify(ticketRepository, times(1)).deleteById(ticketId);
    }
}
