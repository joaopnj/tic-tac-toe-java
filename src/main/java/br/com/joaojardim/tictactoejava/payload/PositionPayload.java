package br.com.joaojardim.tictactoejava.payload;

import lombok.Data;

@Data
public class PositionPayload {
    private Character x;
    private Character y;
}
