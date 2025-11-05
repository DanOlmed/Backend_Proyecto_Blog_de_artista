## 5. Habilitación de CORS
**Contexto:**
Para poder acceder a los endpoints de la API desde el despligue del front en Vercell nos ha solicitado la inclusión de permisos/CORS
**Decisión:**
Crear un archivo de configuración con el cual le damos autorizacion de utilizar los endpoints a cualquier URL que así lo requiera

**Consecuencias:**
* **Positivas:**
    * **Funcionalidad completa:** permite el acceso total e irrestricto del front para poder consumir la API
    * **Apertura a todo proyecto:** aseguramos que desde cualquier URL donde se quiera consumir la API va a poder hacerse.
    
* **Negativas:**
    * Máxima atención: Si la aplicación crece mucho, o si en un futuro se decide aplicar Spring Security, hay que definir que URLs puntuales van a poder acceder a la API

**Estado:** ACEPTADO
