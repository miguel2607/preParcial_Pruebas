package com.universidad.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para Registro de Notas")
class RegistroNotasTest {


    @Test
    @DisplayName("CP-001: Debe aceptar nota en límite mínimo válido (0.0)")
    void debeAceptarNotaEnLimiteMinimoValido() {
        RegistroNotas registro = new RegistroNotas();

        assertDoesNotThrow(() -> {
            registro.registrarNota("Juan Pérez", "Matemáticas", 0.0, "2024-1");
        });
    }

    @Test
    @DisplayName("CP-002: Debe aceptar nota en límite máximo válido (5.0)")
    void debeAceptarNotaEnLimiteMaximoValido() {
        RegistroNotas registro = new RegistroNotas();

        assertDoesNotThrow(() -> {
            registro.registrarNota("María López", "Física", 5.0, "2024-1");
        });
    }

    @Test
    @DisplayName("CP-003: Debe aceptar nota válida intermedia (3.5)")
    void debeAceptarNotaValidaIntermedia() {
        RegistroNotas registro = new RegistroNotas();

        assertDoesNotThrow(() -> {
            registro.registrarNota("Carlos Gómez", "Química", 3.5, "2024-1");
        });
    }

    @Test
    @DisplayName("CP-004: Debe rechazar nota menor al límite mínimo (-1.0)")
    void debeRechazarNotaMenorAlLimiteMinimo() {
        RegistroNotas registro = new RegistroNotas();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            registro.registrarNota("Ana Torres", "Historia", -1.0, "2024-1");
        });

        assertEquals("La nota debe estar entre 0.0 y 5.0", exception.getMessage());
    }

    @Test
    @DisplayName("CP-005: Debe rechazar nota mayor al límite máximo (5.5)")
    void debeRechazarNotaMayorAlLimiteMaximo() {
        RegistroNotas registro = new RegistroNotas();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            registro.registrarNota("Pedro Ramírez", "Inglés", 5.5, "2024-1");
        });

        assertEquals("La nota debe estar entre 0.0 y 5.0", exception.getMessage());
    }
}
