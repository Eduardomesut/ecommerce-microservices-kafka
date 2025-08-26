package com.hiberus.consultadorprendas.application;

import com.hiberus.consultadorprendas.domain.exceptions.PrendaNoExiste;
import com.hiberus.consultadorprendas.domain.model.Prenda;
import com.hiberus.consultadorprendas.domain.repository.PrendaRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConsultarPrenda {

    public static Prenda consultar(String referencia, PrendaRepository prendaRepository) throws PrendaNoExiste {
        return prendaRepository
            .findById(referencia)
            .orElseThrow(PrendaNoExiste::new);
    }
}
