package com.hiberus.consultadorprendas.application;

import com.hiberus.consultadorprendas.domain.model.Prenda;
import com.hiberus.consultadorprendas.domain.repository.PrendaRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CrearPrenda {
    public static void crear(Prenda prenda, PrendaRepository prendaRepository) {
        prendaRepository.save(prenda);
    }
}
