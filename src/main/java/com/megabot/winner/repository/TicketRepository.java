package com.megabot.winner.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.megabot.winner.inteface.model.Ticket;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, UUID> {

}
