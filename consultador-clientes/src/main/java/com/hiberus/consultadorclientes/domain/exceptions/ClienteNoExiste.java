package com.hiberus.consultadorclientes.domain.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClienteNoExiste extends Exception {
    public ClienteNoExiste(String message) {
        super(message);
    }
}
