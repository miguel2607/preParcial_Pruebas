package com.universidad.model;

import java.util.ArrayList;
import java.util.List;

public class RegistroNotas {

    private List<Nota> notas;

    public RegistroNotas() {
        this.notas = new ArrayList<>();
    }

    public void registrarNota(String estudiante, String materia, double nota, String semestre) {
        // Requerimiento 1: Validar que la nota esté entre 0.0 y 5.0
        if (nota < 0.0 || nota > 5.0) {
            throw new IllegalArgumentException("La nota debe estar entre 0.0 y 5.0");
        }

        // Registrar la nota
        notas.add(new Nota(estudiante, materia, nota, semestre));
    }

    // Clase interna para almacenar la información de una nota
    private static class Nota {
        String estudiante;
        String materia;
        double nota;
        String semestre;

        Nota(String estudiante, String materia, double nota, String semestre) {
            this.estudiante = estudiante;
            this.materia = materia;
            this.nota = nota;
            this.semestre = semestre;
        }
    }
}
