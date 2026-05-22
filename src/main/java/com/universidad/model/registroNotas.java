package com.universidad.model;

import com.universidad.exception.NotaDuplicadaException;

import java.util.ArrayList;
import java.util.List;

public class RegistroNotas {

    private static final double NOTA_MINIMA = 0.0;
    private static final double NOTA_MAXIMA = 5.0;
    private static final double NOTA_APROBATORIA = 3.0;
    private static final String MENSAJE_ERROR_RANGO = "La nota debe estar entre 0.0 y 5.0";
    private static final String MENSAJE_ERROR_DUPLICADA = "Ya existe una nota registrada para esta materia en este semestre";

    private final List<Nota> notas;

    public RegistroNotas() {
        this.notas = new ArrayList<>();
    }

    public void registrarNota(String estudiante, String materia, double nota, String semestre) {
        validarParametrosNoNulos(estudiante, materia, semestre);
        validarRangoNota(nota);
        validarNotaDuplicada(estudiante, materia, semestre);

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

    private void validarNotaDuplicada(String estudiante, String materia, String semestre) {
        boolean yaExiste = notas.stream()
                .anyMatch(nota -> nota.estudiante.equals(estudiante)
                        && nota.materia.equals(materia)
                        && nota.semestre.equals(semestre));

        if (yaExiste) {
            throw new NotaDuplicadaException(MENSAJE_ERROR_DUPLICADA);
        }
    }

    public boolean esNotaAprobatoria(double nota) {
        validarRangoNota(nota);
        return nota >= NOTA_APROBATORIA;
    }

    public double calcularPromedio(String estudiante) {
        return notas.stream()
                .filter(nota -> nota.estudiante.equals(estudiante))
                .mapToDouble(nota -> nota.nota)
                .average()
                .orElse(0.0);
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
