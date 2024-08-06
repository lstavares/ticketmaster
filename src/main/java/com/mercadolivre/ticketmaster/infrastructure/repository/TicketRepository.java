package com.mercadolivre.ticketmaster.infrastructure.repository;

import com.mercadolivre.ticketmaster.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}

