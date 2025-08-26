package com.hiberus.enviadorpromociones.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class PromocionTest {
    private final String DEFAULT_MOMBRE= "Black Friday 2022";

    @ParameterizedTest
    @ValueSource(doubles = {-1.00, -250.00, -1000.00})
    void nuncaDeberiaTenerUnDescuentoNegativo(double descuento) {
        // When, Then
        assertThrows(IllegalArgumentException.class, ()-> new Promocion(DEFAULT_MOMBRE, descuento));
    }

    @ParameterizedTest
    @MethodSource("descuentosStream")
    void nuncaDeberiaTenerUnDescuentoQueContengaUnNumeroDeDecimalesMayorA2(double descuentoSource, double descuentoExpected) {
        // When, Then
        Promocion promocion = new Promocion(DEFAULT_MOMBRE, descuentoSource);
        assertEquals(promocion.getDescuento(), descuentoExpected);
    }

    static Stream<Arguments> descuentosStream() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.arguments(12.2345, 12.23),
                org.junit.jupiter.params.provider.Arguments.arguments(12.2999, 12.29)
        );
    }
}
