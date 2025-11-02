## 2.Configuración de Conexión a Base de Datos en Railway

**Contexto:**
Múltiples errores de despliegue (Malformed database URL, Communications link failure) surgieron al intentar configurar la conexión a MySQL usando variables separadas (DATABASE_HOST, DATABASE_USER, etc.) inyectadas por Railway en Spring Boot.

**Decisión:**
Centralizar la configuración de la base de datos en una única variable de entorno, DATABASE_URL, utilizando el formato JDBC completo (jdbc:mysql://user:pass@host:port/db?opciones) y eliminar las variables redundantes en Railway.

**Consecuencias:**
* **Positivas:**
    * **Robustez:** Resuelve el error Malformed database URL al garantizar la sintaxis correcta.
    * **Compatibilidad:** Es el método preferido por Railway y otros proveedores PaaS para el networking interno.
    * **Limpieza:** Simplifica el archivo application.properties en el código.

**Estado:** ACEPTADO
