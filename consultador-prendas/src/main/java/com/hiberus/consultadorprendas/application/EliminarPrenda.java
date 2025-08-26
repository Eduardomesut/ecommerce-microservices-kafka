package com.hiberus.consultadorprendas.application;

import com.hiberus.consultadorprendas.domain.repository.PrendaRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EliminarPrenda {
    public static void eliminar(String referencia, PrendaRepository prendaRepository) {
        prendaRepository
            .findById(referencia)
            .ifPresent(prendaRepository::delete);
    }
}
