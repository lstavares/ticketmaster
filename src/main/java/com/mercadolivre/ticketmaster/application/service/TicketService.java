package com.mercadolivre.ticketmaster.application.service;

import com.mercadolivre.ticketmaster.domain.dto.TicketDTO;
import com.mercadolivre.ticketmaster.domain.entity.Ticket;
import com.mercadolivre.ticketmaster.domain.exception.TicketNotFoundException;
import com.mercadolivre.ticketmaster.domain.mapper.TicketMapper;
import com.mercadolivre.ticketmaster.infrastructure.client.users.UsersClient;
import com.mercadolivre.ticketmaster.infrastructure.client.users.response.UserResponse;
import com.mercadolivre.ticketmaster.infrastructure.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final UsersClient usersClient;
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    public List<TicketDTO> list() {
        return ticketRepository.findAll().stream()
                .map(ticket -> {
                    TicketDTO ticketDTO = ticketMapper.toDto(ticket); // Mapeia para DTO
                    ticketDTO.setUser(usersClient.getUser(ticket.getCustomerId())); // Define o ID do usuário no DTO
                    return ticketDTO;
                })
                .collect(Collectors.toList());
    }

    public TicketDTO get(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException(ticketId));

        if (!isEmpty(ticket))
            ticket.setUser(usersClient.getUser(ticket.getCustomerId()));

        return ticketMapper.toDto(ticket);
    }

    public TicketDTO save(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticket.setCustomerId(getCurrentUser().getId());
        return ticketMapper.toDto(ticketRepository.save(ticket));
    }

    public TicketDTO update(Long ticketId, TicketDTO ticketDTO) {
        Ticket ticketToUpdate = ticketRepository.findById(ticketId).map(ticket -> {
            ticket.setUpdatedAt(now());
            return ticket;
        }).orElseThrow(() -> new TicketNotFoundException(ticketId));

        // Atualiza apenas os campos que foram modificados
        ticketMapper.mergeFromDto(ticketDTO, ticketToUpdate);

        return ticketMapper.toDto(ticketRepository.save(ticketToUpdate));
    }

    public void delete(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

    public UserResponse getCurrentUser() {
        //Aqui eu estou mockando apenas para exemplificar uma integração para consumir um serviço API
        long mockRandomUserId = ThreadLocalRandom.current().nextInt(1, 11);
        return usersClient.getUser(mockRandomUserId);
    }
}
