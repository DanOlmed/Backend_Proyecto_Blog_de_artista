## 3. Uso del Hostname Interno para Conexión

**Contexto:**
La aplicación fallaba con UnknownHostException al intentar acceder a la DB a través de su URL pública o un nombre no reconocido en el contenedor.
**Decisión:**
Configurar la DATABASE_URL para usar el nombre de host interno mysql.railway.internal y el puerto interno (3306), asumiendo que el servicio de la API Java y el de MySQL están enlazados correctamente dentro del proyecto Railway.
**Consecuencias:**
* **Positivas:**
    * **Latencia cero:** La comunicación se realiza en la red privada, no en la pública (más rápido y seguro).
    * **Resolución del host:** La aplicación sabe dónde buscar al servicio dentro del mismo proyecto de Railway.
 **Estado:** ACEPTADO
