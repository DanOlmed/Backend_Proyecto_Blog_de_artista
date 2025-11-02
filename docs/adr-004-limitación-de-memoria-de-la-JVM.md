## 4. Limitación de Memoria de la JVM

**Contexto:**
Los contenedores de aplicaciones Java en plataformas PaaS con límites de recursos (como Railway en el plan gratuito) pueden crashear debido a un OutOfMemoryError si la JVM intenta asignar más memoria de la disponible por defecto.
**Decisión:**
Mantener la variable de entorno JAVA_TOOL_OPTIONS con el valor -Xmx300m para limitar el heap máximo de la JVM a un tamaño seguro y estable.

**Consecuencias:**
* **Positivas:**
    * **Estabilidad:** Previene crashes y reinicios inesperados de la aplicación.
    * **Control de recursos:** Asegura que la aplicación se mantenga dentro de los límites del plan de hosting.
    
* **Negativas:**
    * Potencial cuello de botella: Si la aplicación crece mucho, el límite de 300MB podría necesitar ser ajustado.

**Estado:** ACEPTADO
