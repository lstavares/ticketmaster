package com.mercadolivre.ticketmaster.domain.mapper;

import com.mercadolivre.ticketmaster.domain.dto.TicketDTO;
import com.mercadolivre.ticketmaster.domain.entity.Ticket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TicketMapperTest {

    private final TicketMapper ticketMapper = Mappers.getMapper(TicketMapper.class);

    @Test
    void testToEntity() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTitle("Test Title");
        ticketDTO.setDescription("Test Description");

        Ticket ticket = ticketMapper.toEntity(ticketDTO);

        assertEquals(ticketDTO.getTitle(), ticket.getTitle());
        assertEquals(ticketDTO.getDescription(), ticket.getDescription());
    }

    @Test
    void testToDto() {
        Ticket ticket = new Ticket();
        ticket.setTitle("Test Title");
        ticket.setDescription("Test Description");

        TicketDTO ticketDTO = ticketMapper.toDto(ticket);

        assertEquals(ticket.getTitle(), ticketDTO.getTitle());
        assertEquals(ticket.getDescription(), ticketDTO.getDescription());
    }

    @Test
    void testMergeFromDto() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTitle("Updated Title");
        Ticket ticket = new Ticket();
        ticket.setTitle("Old Title");

        ticketMapper.mergeFromDto(ticketDTO, ticket);

        assertEquals(ticketDTO.getTitle(), ticket.getTitle());
    }
}
