package com.kodilla.cards.repository;

import com.kodilla.cards.entity.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Long> {
    List<Card> findByCustomerId(long customerId);
}
