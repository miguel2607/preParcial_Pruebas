Feature: Gestión de Registro de Notas Académicas
  Como coordinador académico de la Universidad Regional del Sur
  Quiero registrar y consultar las notas de los estudiantes
  Para llevar un control preciso del rendimiento académico y evitar duplicados

  Background:
    Given que el sistema de registro de notas está disponible

  # ========== REQUERIMIENTO 2: Clasificación Aprobado/Reprobado ==========

  @smoke @critical
  Scenario: Estudiante aprueba con nota exactamente en el límite
    Given que un estudiante tiene una nota de 3.0
    When consulto si la nota es aprobatoria
    Then el sistema debe indicar que la nota es "Aprobada"

  @regression
  Scenario: Estudiante reprueba por décimas
    Given que un estudiante tiene una nota de 2.9
    When consulto si la nota es aprobatoria
    Then el sistema debe indicar que la nota es "Reprobada"

  @smoke
  Scenario Outline: Verificar clasificación de diferentes notas
    Given que un estudiante tiene una nota de <nota>
    When consulto si la nota es aprobatoria
    Then el sistema debe indicar que la nota es "<resultado>"

    Examples:
      | nota | resultado  |
      | 0.0  | Reprobada  |
      | 1.5  | Reprobada  |
      | 2.9  | Reprobada  |
      | 3.0  | Aprobada   |
      | 3.5  | Aprobada   |
      | 4.5  | Aprobada   |
      | 5.0  | Aprobada   |

  # ========== REQUERIMIENTO 3: Cálculo de Promedio ==========

  @critical
  Scenario: Calcular promedio de estudiante con varias notas
    Given que el estudiante "Laura Martínez" tiene las siguientes notas registradas:
      | materia      | nota | semestre |
      | Matemáticas  | 4.0  | 2024-1   |
      | Física       | 3.5  | 2024-1   |
      | Química      | 4.5  | 2024-1   |
    When consulto el promedio del estudiante "Laura Martínez"
    Then el sistema debe mostrar un promedio de 4.0

  @smoke
  Scenario: Estudiante sin notas debe tener promedio cero
    Given que el estudiante "Roberto Sánchez" no tiene notas registradas
    When consulto el promedio del estudiante "Roberto Sánchez"
    Then el sistema debe mostrar un promedio de 0.0

  @regression
  Scenario: Calcular promedio con una sola materia
    Given que el estudiante "Carmen Díaz" tiene las siguientes notas registradas:
      | materia | nota | semestre |
      | Cálculo | 3.8  | 2024-1   |
    When consulto el promedio del estudiante "Carmen Díaz"
    Then el sistema debe mostrar un promedio de 3.8

  # ========== REQUERIMIENTO 4: Prevención de Notas Duplicadas ==========

  @critical
  Scenario: No se permite registrar nota duplicada en la misma materia y semestre
    Given que el estudiante "Miguel Rojas" ya tiene una nota registrada en "Física" del semestre "2024-1"
    When intento registrar otra nota de 4.0 para "Miguel Rojas" en "Física" del semestre "2024-1"
    Then el sistema debe rechazar el registro con el mensaje "Ya existe una nota registrada para esta materia en este semestre"

  @smoke
  Scenario: Se permite registrar la misma materia en diferente semestre
    Given que el estudiante "Andrea Ruiz" tiene una nota registrada en "Química" del semestre "2024-1"
    When registro una nota de 3.5 para "Andrea Ruiz" en "Química" del semestre "2024-2"
    Then el sistema debe aceptar el registro exitosamente

  @regression
  Scenario: Primera nota de un estudiante en una materia
    Given que el estudiante "Fernando Silva" no tiene notas en "Estadística"
    When registro una nota de 4.2 para "Fernando Silva" en "Estadística" del semestre "2024-1"
    Then el sistema debe aceptar el registro exitosamente
