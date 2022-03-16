package com.kodilla.cards.controller;

import com.kodilla.cards.dto.CardDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class GetCardsResponse {

    public List<CardDto> cards;

}