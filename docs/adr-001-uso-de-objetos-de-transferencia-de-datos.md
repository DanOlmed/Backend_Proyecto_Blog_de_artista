## 1. Adopción del Patrón DTO para la Capa de Presentación

**Contexto:**
Necesidad de desacoplar las Entidades JPA (modelos de la base de datos) de la capa de presentación RESTful para evitar la exposición de datos sensibles y manejar eficientemente las relaciones recursivas (ciclos infinitos de serialización).

**Decisión:**
Implementar **Data Transfer Objects (DTOs)** para todas las operaciones GET expuestas por los Controladores. La capa de Servicio será responsable de mapear las Entidades (`Disco`, `Artista`) a sus respectivos DTOs (`DiscoDTO`, `ArtistaDTO`).

**Consecuencias:**
* **Positivas:**
    * **Seguridad:** Previene la exposición de datos internos o de Hibernate.
    * **Eficiencia:** Resuelve el error de serialización cíclica (`StackOverflowError`) y reduce el tamaño de la respuesta JSON (menos *overhead*).
    * **Mantenibilidad:** Permite modificar el esquema de la base de datos sin romper el contrato de la API.
* **Negativas:**
    * Requiere un *overhead* de código para el mapeo (aunque se gestiona con Mappers).

**Estado:** ACEPTADO
