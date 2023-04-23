package com.alga.algarworks.Exception;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InvalidDataException extends RuntimeException {
    public final String name;
    public final String userMessage;


    public InvalidDataException(String name, String userMessage) {
        this.name = "amount";
        this.userMessage = "O valor é obrigatório";
    }


}




