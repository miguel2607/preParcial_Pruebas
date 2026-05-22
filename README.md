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
