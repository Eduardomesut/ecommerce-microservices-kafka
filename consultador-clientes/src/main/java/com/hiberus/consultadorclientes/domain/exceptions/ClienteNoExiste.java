package com.hiberus.consultadorclientes.domain.exceptions;

public class ClienteNoExiste extends RuntimeException {
    public ClienteNoExiste(String message) {
        super(message);
    }
}
