package com.mercadolivre.ticketmaster.application.service;

import com.mercadolivre.ticketmaster.domain.dto.TicketDTO;
import com.mercadolivre.ticketmaster.domain.entity.Ticket;
import com.mercadolivre.ticketmaster.domain.exception.TicketNotFoundException;
import com.mercadolivre.ticketmaster.domain.mapper.TicketMapper;
import com.mercadolivre.ticketmaster.infrastructure.client.users.UsersClient;
import com.mercadolivre.ticketmaster.infrastructure.client.users.response.UserResponse;
import com.mercadolivre.ticketmaster.infrastructure.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.mercadolivre.ticketmaster.application.utils.Helper.LogHelper.putObjectInMDC;
import static java.time.LocalDateTime.now;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final Logger logger = getLogger(this.getClass());

    private final UsersClient usersClient;
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    public List<TicketDTO> list() {
        logger.info("Retrieving ticket list");
        List<TicketDTO> ticketList = ticketRepository.findAll().stream()
                .map(ticket -> {
                    TicketDTO ticketDTO = ticketMapper.toDto(ticket); // Mapeia para DTO
                    ticketDTO.setUser(usersClient.getUser(ticket.getCustomerId())); // Define o ID do usuário no DTO
                    return ticketDTO;
                })
                .collect(Collectors.toList());
        putObjectInMDC("payload", Map.of("payload", ticketList));
        logger.info("Ticket list retrieved");
        return ticketList;
    }

    public TicketDTO get(Long ticketId) {
        logger.info("Retrieving ticket with id {}", ticketId);
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException(ticketId));

        if (!isEmpty(ticket))
            ticket.setUser(usersClient.getUser(ticket.getCustomerId()));

        TicketDTO ticketDTO = ticketMapper.toDto(ticket);
        putObjectInMDC("payload", ticketDTO);
        logger.info("Ticket with id {} retrieved", ticketId);
        return ticketDTO;
    }

    public TicketDTO save(TicketDTO ticketDTO) {
        logger.info("Saving ticket {}", ticketDTO);
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticket.setCustomerId(getCurrentUser().getId());
        TicketDTO ticketCreated = ticketMapper.toDto(ticketRepository.save(ticket));
        putObjectInMDC("payload", ticketCreated);
        logger.info("Ticket with id {} saved", ticketDTO.getId());
        return ticketCreated;
    }

    public TicketDTO update(Long ticketId, TicketDTO ticketDTO) {
        logger.info("Updating ticket id {} with {}", ticketId, ticketDTO);
        Ticket ticketToUpdate = ticketRepository.findById(ticketId).map(ticket -> {
            ticket.setUpdatedAt(now());
            return ticket;
        }).orElseThrow(() -> new TicketNotFoundException(ticketId));

        // Atualiza apenas os campos que foram modificados
        ticketMapper.mergeFromDto(ticketDTO, ticketToUpdate);

        TicketDTO ticketUpdated = ticketMapper.toDto(ticketRepository.save(ticketToUpdate));
        putObjectInMDC("payload", ticketUpdated);
        logger.info("Ticket with id {} updated", ticketDTO.getId());
        return ticketUpdated;
    }

    public void delete(Long ticketId) {
        logger.info("Deleting ticket id {}", ticketId);
        ticketRepository.deleteById(ticketId);
        logger.info("Deleted ticket id {}", ticketId);
    }

    public UserResponse getCurrentUser() {
        //Aqui eu estou mockando apenas para exemplificar uma integração para consumir um serviço API
        long mockRandomUserId = ThreadLocalRandom.current().nextInt(1, 11);
        return usersClient.getUser(mockRandomUserId);
    }
}
