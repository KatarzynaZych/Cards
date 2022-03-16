package com.kodilla.cards.controller;


import com.kodilla.cards.dto.CardDto;
import com.kodilla.cards.entity.Card;
import com.kodilla.cards.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/v1/")
public class CardsController {

    @Value("${application.allow-get-cards}")
    private boolean allowGetCards;

    @Autowired
    CardRepository cardRepository;

    @GetMapping("/card/customer/{idCustomer}")
    public GetCardsResponse getCustomerCards(@PathVariable Long idCustomer) {
        if (!allowGetCards) {
            log.info("GETTING CARDS IS DISABLED");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Getting cards is disabled");
        }

        List<Card> cards = cardRepository.findByCustomerId(idCustomer);
        return GetCardsResponse.of(cards.stream().map(this::convertToDto).collect(Collectors.toList()));
    }

    private CardDto convertToDto(Card card) {
        return CardDto.builder().id(card.getId()).cardHolder(card.getCardHolder()).cardNumber(card.getCardNumber()).expirationDate(card.getExpirationDate()).build();
    }
}
