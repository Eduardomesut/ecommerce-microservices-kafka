package com.hiberus.consultadorprendas.application;

import com.hiberus.consultadorprendas.domain.model.Prenda;
import com.hiberus.consultadorprendas.domain.repository.PrendaRepository;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConsultarTodasLasPrendas {
    public static List<Prenda> consultar(PrendaRepository prendaRepository) {
        return prendaRepository.findAll();
    }
}
