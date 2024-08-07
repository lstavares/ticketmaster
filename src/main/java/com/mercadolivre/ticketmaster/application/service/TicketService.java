package com.mercadolivre.ticketmaster.application.service;

import com.mercadolivre.ticketmaster.domain.Ticket;
import com.mercadolivre.ticketmaster.domain.exception.TicketNotFoundException;
import com.mercadolivre.ticketmaster.infrastructure.client.users.UsersClient;
import com.mercadolivre.ticketmaster.infrastructure.client.users.response.UserResponse;
import com.mercadolivre.ticketmaster.infrastructure.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final UsersClient usersClient;
    private final TicketRepository ticketRepository;

    public List<Ticket> list() {
        List<Ticket> ticketList = ticketRepository.findAll();
        ticketList.forEach(ticket -> ticket.setUser(getCurrentUser()));
        return ticketList;
    }

    public Ticket get(Long ticketId) {
        UserResponse userResponse = getCurrentUser();

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException(ticketId));

        if (!isEmpty(ticket))
            ticket.setUser(userResponse);

        return ticket;
    }

    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
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
