package br.com.joaojardim.tictactoejava.model;


import br.com.joaojardim.tictactoejava.payload.PositionPayload;
import br.com.joaojardim.tictactoejava.payload.RequestMovimentPayLoad;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "game")
@Data
public class Game {

    @Id
    private String id;
    private Character winner;
    private Character turn;
    private Character[][] table;

    public Game(Character winner, Character turn, Character[][] table) {
        this.winner = winner;
        this.turn = turn;
        this.table = table;
    }
}