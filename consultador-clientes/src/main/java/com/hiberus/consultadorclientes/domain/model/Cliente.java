package com.hiberus.consultadorclientes.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public final class Cliente {
    @Id
    String referencia;
    String nombre;

    @ElementCollection(fetch = FetchType.EAGER,targetClass=String.class)
    List<String> categorias;

    public Cliente(String referencia, String nombre, List<String> categorias) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.categorias = categorias;
    }
}
