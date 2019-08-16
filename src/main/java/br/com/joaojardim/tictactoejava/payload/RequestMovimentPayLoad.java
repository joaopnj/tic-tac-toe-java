package br.com.joaojardim.tictactoejava.payload;

import lombok.Data;

@Data
public class RequestMovimentPayLoad {
    private Character player;
    private PositionPayload position;
}
