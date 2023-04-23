package com.alga.algarworks.Exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter

public class ErrorObject {
    private final int status;
    private final String timestamp;
    private final String type;
    private final String title;
    private final String detail;
    private List<InvalidDataException> objects;


    public ErrorObject(String name, String userMessage) {
        super();
        this.status = 400;
        this.timestamp = LocalDateTime.now().toString();
        this.type = "https://algaworks.com/dados-invalidos";
        this.title = "Dados inválidos";
        this.detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
        this.objects = List.of(new InvalidDataException(name, userMessage));

    }

}
