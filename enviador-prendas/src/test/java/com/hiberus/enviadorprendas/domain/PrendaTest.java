package com.hiberus.enviadorprendas.domain;

import static com.hiberus.enviadorprendas.domain.model.Categoria.Hombre;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.hiberus.enviadorprendas.domain.model.Categoria;
import com.hiberus.enviadorprendas.domain.model.Prenda;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class PrendaTest {
    private final String DEFAULT_REFERENCIA = "S123456789";
    private final double DEFAULT_PRECIO = 12.00;
    private final List<Categoria> DEFAULT_CATEGORIAS = new ArrayList<>(Arrays.asList(Hombre));

    @ParameterizedTest
    @ValueSource(strings = {"", "S123", "S12345678910111213141516"})
    void nuncaDeberiaTenerUnaReferenciaConLongitudDistintaA10(String referencia) {
        // When, Then
        assertThrows( IllegalArgumentException.class,
                ()-> new Prenda(referencia, DEFAULT_PRECIO, DEFAULT_CATEGORIAS) );
    }

    @ParameterizedTest
    @ValueSource(strings = {"A123456789", "0123456789", "Z123456789"})
    void nuncaDeberiaTenerUnaReferenciaConPrimerCaracterDistintoA_S_M_L(String input) {
        // When, Then
        assertThrows(IllegalArgumentException.class, ()->new Prenda(input, DEFAULT_PRECIO, DEFAULT_CATEGORIAS));
    }

    @ParameterizedTest
    @ValueSource(strings = {"S***456789", "M12345!!!!", "L_)345678_"})
    void nuncaDeberiaTenerUnaReferenciaQueContengaUnCaracterNoAlfanumerico(String input) {
        // When, Then
        assertThrows(IllegalArgumentException.class, ()->new Prenda(input, DEFAULT_PRECIO, DEFAULT_CATEGORIAS));
    }



    @ParameterizedTest
    @ValueSource(doubles = {-1.00, -250.00, -1000.00})
    void nuncaDeberiaTenerUnPrecioNegativo(double precio) {
        // When, Then
        assertThrows(IllegalArgumentException.class, ()-> new Prenda(DEFAULT_REFERENCIA, precio, DEFAULT_CATEGORIAS));
    }


    @ParameterizedTest
    @MethodSource("preciosStream")
    void nuncaDeberiaTenerUnPrecioQueContengaUnNumeroDeDecimalesMayorA2(double precioSource, double precioExpected) {
        // When, Then
        Prenda prenda = new Prenda(DEFAULT_REFERENCIA, precioSource, DEFAULT_CATEGORIAS);
        assertEquals(prenda.getPrecio(), precioExpected);
    }

    static Stream<Arguments> preciosStream() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.arguments(12.2345, 12.23),
                org.junit.jupiter.params.provider.Arguments.arguments(12.2999, 12.29)
        );
    }
}
