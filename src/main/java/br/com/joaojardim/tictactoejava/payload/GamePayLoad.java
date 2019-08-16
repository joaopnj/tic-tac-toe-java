package br.com.joaojardim.tictactoejava.payload;

import lombok.Data;

@Data
public class GamePayLoad {
    private String id;
    private Character firstPlayer;
}
