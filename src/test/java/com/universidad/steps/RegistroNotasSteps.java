package com.universidad.steps;

import com.universidad.exception.NotaDuplicadaException;
import com.universidad.model.RegistroNotas;
import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RegistroNotasSteps {

    private RegistroNotas registro;
    private double notaConsultada;
    private boolean resultadoAprobacion;
    private double promedioCalculado;
    private Exception excepcionCapturada;
    private boolean registroExitoso;

    // ========== ANTECEDENTES ==========

    @Given("que el sistema de registro de notas está disponible")
    public void queElSistemaDeRegistroDeNotasEstaDisponible() {
        registro = new RegistroNotas();
        excepcionCapturada = null;
        registroExitoso = false;
    }

    // ========== REQUERIMIENTO 2: Clasificación Aprobado/Reprobado ==========

    @Given("que un estudiante tiene una nota de {double}")
    public void queUnEstudianteTieneUnaNotaDe(double nota) {
        this.notaConsultada = nota;
    }

    @When("consulto si la nota es aprobatoria")
    public void consultoSiLaNotaEsAprobatoria() {
        resultadoAprobacion = registro.esNotaAprobatoria(notaConsultada);
    }

    @Then("el sistema debe indicar que la nota es {string}")
    public void elSistemaDebeIndicarQueLaNotaEs(String estadoEsperado) {
        if (estadoEsperado.equals("Aprobada")) {
            assertTrue(resultadoAprobacion,
                "La nota " + notaConsultada + " debería ser aprobatoria");
        } else {
            assertFalse(resultadoAprobacion,
                "La nota " + notaConsultada + " debería ser reprobada");
        }
    }

    // ========== REQUERIMIENTO 3: Cálculo de Promedio ==========

    @Given("que el estudiante {string} tiene las siguientes notas registradas:")
    public void queElEstudianteTieneLasSiguientesNotasRegistradas(String estudiante, DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        for (Map<String, String> row : rows) {
            String materia = row.get("materia");
            double nota = Double.parseDouble(row.get("nota"));
            String semestre = row.get("semestre");
            registro.registrarNota(estudiante, materia, nota, semestre);
        }
    }

    @Given("que el estudiante {string} no tiene notas registradas")
    public void queElEstudianteNoTieneNotasRegistradas(String estudiante) {
        // No registrar nada, el sistema ya está limpio
    }

    @When("consulto el promedio del estudiante {string}")
    public void consultoElPromedioDelEstudiante(String estudiante) {
        promedioCalculado = registro.calcularPromedio(estudiante);
    }

    @Then("el sistema debe mostrar un promedio de {double}")
    public void elSistemaDebeMostrarUnPromedioDe(double promedioEsperado) {
        assertEquals(promedioEsperado, promedioCalculado, 0.01,
            "El promedio calculado no coincide con el esperado");
    }

    // ========== REQUERIMIENTO 4: Prevención de Notas Duplicadas ==========

    @Given("que el estudiante {string} ya tiene una nota registrada en {string} del semestre {string}")
    public void queElEstudianteYaTieneUnaNotaRegistradaEnDelSemestre(
            String estudiante, String materia, String semestre) {
        registro.registrarNota(estudiante, materia, 3.5, semestre);
    }

    @When("intento registrar otra nota de {double} para {string} en {string} del semestre {string}")
    public void intentoRegistrarOtraNotaDeParaEnDelSemestre(
            double nota, String estudiante, String materia, String semestre) {
        try {
            registro.registrarNota(estudiante, materia, nota, semestre);
            registroExitoso = true;
        } catch (Exception e) {
            excepcionCapturada = e;
            registroExitoso = false;
        }
    }

    @Then("el sistema debe rechazar el registro con el mensaje {string}")
    public void elSistemaDebeRechazarElRegistroConElMensaje(String mensajeEsperado) {
        assertNotNull(excepcionCapturada,
            "Se esperaba una excepción al intentar registrar una nota duplicada");
        assertTrue(excepcionCapturada instanceof NotaDuplicadaException,
            "Se esperaba una NotaDuplicadaException");
        assertTrue(excepcionCapturada.getMessage().contains(mensajeEsperado),
            "El mensaje de error debe contener: " + mensajeEsperado);
    }

    @Given("que el estudiante {string} tiene una nota registrada en {string} del semestre {string}")
    public void queElEstudianteTieneUnaNotaRegistradaEnDelSemestre(
            String estudiante, String materia, String semestre) {
        registro.registrarNota(estudiante, materia, 3.2, semestre);
    }

    @When("registro una nota de {double} para {string} en {string} del semestre {string}")
    public void registroUnaNotaDeParaEnDelSemestre(
            double nota, String estudiante, String materia, String semestre) {
        try {
            registro.registrarNota(estudiante, materia, nota, semestre);
            registroExitoso = true;
        } catch (Exception e) {
            excepcionCapturada = e;
            registroExitoso = false;
        }
    }

    @Then("el sistema debe aceptar el registro exitosamente")
    public void elSistemaDebeAceptarElRegistroExitosamente() {
        assertNull(excepcionCapturada,
            "No se esperaba ninguna excepción: " +
            (excepcionCapturada != null ? excepcionCapturada.getMessage() : ""));
        assertTrue(registroExitoso,
            "El registro debería haberse completado exitosamente");
    }

    @Given("que el estudiante {string} no tiene notas en {string}")
    public void queElEstudianteNoTieneNotasEn(String estudiante, String materia) {
        // No registrar nada, el sistema ya está limpio
    }
}
