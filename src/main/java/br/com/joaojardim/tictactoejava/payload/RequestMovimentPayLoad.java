package br.com.joaojardim.tictactoejava.payload;

import lombok.Data;

@Data
public class RequestMovimentPayLoad {
    private String player;
    private PositionPayload position;
}
