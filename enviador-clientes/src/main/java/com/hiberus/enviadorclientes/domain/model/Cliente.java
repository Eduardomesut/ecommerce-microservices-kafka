package com.hiberus.enviadorclientes.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
public final class Cliente {

    private final String FORMATO_REFERNCIA_PERMITIDO = "^[SML][a-zA-Z0-9]{9}";


    String referencia;
    String nombre;
    List<Categoria> categorias;

    public Cliente(String referencia, String nombre, List<Categoria> categorias) {
        validarReferencia(referencia);
        this.referencia = referencia;
        this.nombre = nombre;
        this.categorias = categorias;
    }

    private void validarReferencia(String referencia) {
        if(!referencia.matches(FORMATO_REFERNCIA_PERMITIDO)) throw new IllegalArgumentException();
    }

}
