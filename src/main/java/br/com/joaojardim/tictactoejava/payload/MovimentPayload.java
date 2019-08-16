package br.com.joaojardim.tictactoejava.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class MovimentPayload {
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String winner;
}
