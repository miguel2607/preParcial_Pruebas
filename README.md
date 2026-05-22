## Requerimiento 1.1
La nota debe estar entre **0.0 y 5.0**.

| Nombre de la partición | Tipo | Rango que cubre | Valor representativo | Resultado esperado |
------------------------------------------------------------------------------------------------
| Nota válida mínima |   Válida | 0.0 ≤ nota ≤ 5.0 | 0.0 | El sistema acepta la nota |
| Nota válida intermedia| Válida | 0.0 ≤ nota ≤ 5.0 | 3.5 | El sistema acepta la nota |
| Nota válida máxima |   Válida | 0.0 ≤ nota ≤ 5.0 | 5.0 | El sistema acepta la nota |
| Nota menor al mínimo | Inválida | nota < 0.0 | -1.0 | El sistema rechaza la nota y muestra error |
| Nota mayor al máximo | Inválida | nota > 5.0 | 5.5 | El sistema rechaza la nota y muestra error |
| Valor no numérico |   Inválida | Letras o símbolos | "abc" | El sistema rechaza el dato y muestra error |
| Valor vacío o nulo |  Inválida | Campo vacío | "" | El sistema solicita ingresar una nota válida |

## Requerimiento 1.2

La nota debe estar entre **0.0 y 5.0**.

## Valores críticos en los límites

| Límite | Valor de prueba | Dentro/Fuera del rango | Resultado esperado |
|---------------------------------------------------------------------|
| Justo antes del mínimo | -0.1 | Fuera | El sistema rechaza la nota y muestra error |
| Límite mínimo exacto | 0.0 | Dentro | El sistema acepta la nota |
| Justo después del mínimo | 0.1 | Dentro | El sistema acepta la nota |
| Justo antes del máximo | 4.9 | Dentro | El sistema acepta la nota |
| Límite máximo exacto | 5.0 | Dentro | El sistema acepta la nota |
| Justo después del máximo | 5.1 | Fuera | El sistema rechaza la nota y muestra error |


- ## Requerimiento 1.3
# Preguntas al Product Owner
¿Qué se considera exactamente una nota duplicada?
¿El sistema debe bloquear automáticamente la nota duplicada o solo mostrar una advertencia?
¿La validación de duplicados aplica únicamente al momento de crear una nota o también al editarla?
¿La validación de duplicados distingue mayúsculas, espacios o formatos diferentes?

---

# Casos de Prueba

## Especificación de Requerimientos

**Requerimiento 1:** La nota debe estar entre 0.0 y 5.0
**Requerimiento 2:** El sistema debe clasificar si una nota es aprobatoria (>= 3.0)
**Requerimiento 3:** El sistema debe calcular el promedio de un estudiante
**Requerimiento 4:** El sistema no debe permitir registrar notas duplicadas para la misma materia en el mismo semestre

## Tabla de Casos de Prueba

| ID | Requerimiento | Descripción | Precondición | Datos de entrada | Pasos | Resultado esperado | Tipo |
|----|---------------|-------------|--------------|------------------|-------|-------------------|------|
| CP-001 | Req 1 | Validar nota en límite mínimo válido | Sistema de registro iniciado | Nota: 0.0, Materia: "Matemáticas", Estudiante: "Juan Pérez" | 1. Ingresar al sistema de registro<br>2. Seleccionar estudiante "Juan Pérez"<br>3. Ingresar materia "Matemáticas"<br>4. Ingresar nota 0.0<br>5. Guardar registro | El sistema acepta la nota 0.0 y muestra mensaje "Nota registrada exitosamente" | Borde |
| CP-002 | Req 1 | Validar nota en límite máximo válido | Sistema de registro iniciado | Nota: 5.0, Materia: "Física", Estudiante: "María López" | 1. Ingresar al sistema de registro<br>2. Seleccionar estudiante "María López"<br>3. Ingresar materia "Física"<br>4. Ingresar nota 5.0<br>5. Guardar registro | El sistema acepta la nota 5.0 y muestra mensaje "Nota registrada exitosamente" | Borde |
| CP-003 | Req 1 | Validar nota válida intermedia | Sistema de registro iniciado | Nota: 3.5, Materia: "Química", Estudiante: "Carlos Gómez" | 1. Ingresar al sistema de registro<br>2. Seleccionar estudiante "Carlos Gómez"<br>3. Ingresar materia "Química"<br>4. Ingresar nota 3.5<br>5. Guardar registro | El sistema acepta la nota 3.5 y muestra mensaje "Nota registrada exitosamente" | Positivo |
| CP-004 | Req 1 | Validar nota menor al límite mínimo | Sistema de registro iniciado | Nota: -1.0, Materia: "Historia", Estudiante: "Ana Torres" | 1. Ingresar al sistema de registro<br>2. Seleccionar estudiante "Ana Torres"<br>3. Ingresar materia "Historia"<br>4. Ingresar nota -1.0<br>5. Intentar guardar registro | El sistema rechaza la nota y muestra mensaje de error "La nota debe estar entre 0.0 y 5.0" | Negativo |
| CP-005 | Req 1 | Validar nota mayor al límite máximo | Sistema de registro iniciado | Nota: 5.5, Materia: "Inglés", Estudiante: "Pedro Ramírez" | 1. Ingresar al sistema de registro<br>2. Seleccionar estudiante "Pedro Ramírez"<br>3. Ingresar materia "Inglés"<br>4. Ingresar nota 5.5<br>5. Intentar guardar registro | El sistema rechaza la nota y muestra mensaje de error "La nota debe estar entre 0.0 y 5.0" | Negativo |
| CP-006 | Req 2 | Validar nota justo en el límite de aprobación | Sistema de registro iniciado | Nota: 3.0, Materia: "Programación", Estudiante: "Luis Morales" | 1. Ingresar al sistema de registro<br>2. Seleccionar estudiante "Luis Morales"<br>3. Ingresar materia "Programación"<br>4. Ingresar nota 3.0<br>5. Guardar registro<br>6. Consultar estado de la nota | El sistema acepta la nota 3.0 y la clasifica como "Aprobada" | Borde |
| CP-007 | Req 2 | Validar nota justo debajo del límite de aprobación | Sistema de registro iniciado | Nota: 2.9, Materia: "Bases de Datos", Estudiante: "Sofia Castro" | 1. Ingresar al sistema de registro<br>2. Seleccionar estudiante "Sofia Castro"<br>3. Ingresar materia "Bases de Datos"<br>4. Ingresar nota 2.9<br>5. Guardar registro<br>6. Consultar estado de la nota | El sistema acepta la nota 2.9 y la clasifica como "Reprobada" | Borde |
| CP-008 | Req 2 | Validar nota aprobatoria alta | Sistema de registro iniciado | Nota: 4.5, Materia: "Algoritmos", Estudiante: "Diego Vargas" | 1. Ingresar al sistema de registro<br>2. Seleccionar estudiante "Diego Vargas"<br>3. Ingresar materia "Algoritmos"<br>4. Ingresar nota 4.5<br>5. Guardar registro<br>6. Consultar estado de la nota | El sistema acepta la nota 4.5 y la clasifica como "Aprobada" | Positivo |
| CP-009 | Req 3 | Calcular promedio de estudiante con múltiples notas | Sistema de registro iniciado con notas previas | Estudiante: "Laura Martínez" tiene notas: 4.0, 3.5, 4.5 | 1. Ingresar al sistema de consultas<br>2. Seleccionar estudiante "Laura Martínez"<br>3. Solicitar cálculo de promedio<br>4. Verificar resultado | El sistema calcula y muestra el promedio: 4.0 | Positivo |
| CP-010 | Req 3 | Calcular promedio de estudiante sin notas registradas | Sistema de registro iniciado | Estudiante: "Roberto Sánchez" sin notas previas | 1. Ingresar al sistema de consultas<br>2. Seleccionar estudiante "Roberto Sánchez"<br>3. Solicitar cálculo de promedio<br>4. Verificar resultado | El sistema muestra mensaje "El estudiante no tiene notas registradas" o promedio: 0.0 | Borde |
| CP-011 | Req 3 | Calcular promedio con una sola nota | Sistema de registro iniciado | Estudiante: "Carmen Díaz" tiene nota: 3.8 en "Cálculo" | 1. Ingresar al sistema de consultas<br>2. Seleccionar estudiante "Carmen Díaz"<br>3. Solicitar cálculo de promedio<br>4. Verificar resultado | El sistema calcula y muestra el promedio: 3.8 | Positivo |
| CP-012 | Req 4 | Intentar registrar nota duplicada en la misma materia y semestre | Sistema de registro iniciado | Estudiante: "Miguel Rojas" ya tiene nota en "Física" semestre "2024-1" | 1. Ingresar al sistema de registro<br>2. Seleccionar estudiante "Miguel Rojas"<br>3. Seleccionar materia "Física"<br>4. Seleccionar semestre "2024-1"<br>5. Ingresar nota 4.0<br>6. Intentar guardar registro | El sistema rechaza el registro y muestra mensaje "Ya existe una nota registrada para esta materia en este semestre" | Negativo |
| CP-013 | Req 4 | Registrar nota en la misma materia pero diferente semestre | Sistema de registro iniciado | Estudiante: "Andrea Ruiz" tiene nota en "Química" semestre "2024-1" | 1. Ingresar al sistema de registro<br>2. Seleccionar estudiante "Andrea Ruiz"<br>3. Seleccionar materia "Química"<br>4. Seleccionar semestre "2024-2"<br>5. Ingresar nota 3.5<br>6. Guardar registro | El sistema acepta la nota y muestra mensaje "Nota registrada exitosamente" | Positivo |
| CP-014 | Req 4 | Registrar primera nota de una materia | Sistema de registro iniciado | Estudiante: "Fernando Silva" sin notas en "Estadística" | 1. Ingresar al sistema de registro<br>2. Seleccionar estudiante "Fernando Silva"<br>3. Seleccionar materia "Estadística"<br>4. Seleccionar semestre "2024-1"<br>5. Ingresar nota 4.2<br>6. Guardar registro | El sistema acepta la nota y muestra mensaje "Nota registrada exitosamente" | Positivo |
