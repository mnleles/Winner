package com.megabot.winner.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.megabot.winner.inteface.model.Stats;

@Repository
public interface StatsRepository extends MongoRepository<Stats, UUID> {

}
