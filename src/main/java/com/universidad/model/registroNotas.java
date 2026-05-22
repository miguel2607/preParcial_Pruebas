package com.universidad.model;

import java.util.ArrayList;
import java.util.List;

public class RegistroNotas {

    private static final double NOTA_MINIMA = 0.0;
    private static final double NOTA_MAXIMA = 5.0;
    private static final String MENSAJE_ERROR_RANGO = "La nota debe estar entre 0.0 y 5.0";

    private final List<Nota> notas;

    public RegistroNotas() {
        this.notas = new ArrayList<>();
    }

    public void registrarNota(String estudiante, String materia, double nota, String semestre) {
        validarParametrosNoNulos(estudiante, materia, semestre);
        validarRangoNota(nota);

        notas.add(new Nota(estudiante, materia, nota, semestre));
    }

    private void validarParametrosNoNulos(String estudiante, String materia, String semestre) {
        if (estudiante == null || estudiante.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estudiante no puede ser nulo o vacío");
        }
        if (materia == null || materia.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la materia no puede ser nulo o vacío");
        }
        if (semestre == null || semestre.trim().isEmpty()) {
            throw new IllegalArgumentException("El semestre no puede ser nulo o vacío");
        }
    }

    private void validarRangoNota(double nota) {
        if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            throw new IllegalArgumentException(MENSAJE_ERROR_RANGO);
        }
    }

    public boolean esNotaAprobatoria(double nota) {
        // Requerimiento 2: Aprueba con nota >= 3.0
        return nota >= 3.0;
    }

    // Clase interna para almacenar la información de una nota
    private static class Nota {
        private final String estudiante;
        private final String materia;
        private final double nota;
        private final String semestre;

        Nota(String estudiante, String materia, double nota, String semestre) {
            this.estudiante = estudiante;
            this.materia = materia;
            this.nota = nota;
            this.semestre = semestre;
        }
    }
}
