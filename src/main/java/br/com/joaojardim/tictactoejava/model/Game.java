package br.com.joaojardim.tictactoejava.model;


import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "game")
@Getter
public class Game {

    @Id
    private String id;
    private String winner;
    private String turn;
    private String[][] board;

    public Game(String winner, String turn, String[][] board) {
        this.winner = winner;
        this.turn = turn;
        this.board = board;
    }
}