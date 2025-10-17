<p align="center">
  <img src="https://res.cloudinary.com/dpytht2dn/image/upload/v1760309127/ricardo-iorio-v8_no_se_rindan_hwpo0v.webp" alt="Banner del proyecto" width="800"/>
</p>

# 📝 DOCUMENTACIÓN API REST: IORIO WEB BACKEND (v1.0)

Este documento es el contrato de la API REST que el frontend debe consumir.  
Todos los datos son entregados mediante **DTOs (Objetos de Transferencia de Datos)** optimizados.

---

## 1. CONFIGURACIÓN Y ACCESO

| Parámetro             | Valor                         | Notas                                     |
|------------------------|--------------------------------|-------------------------------------------|
| **URL Base (Dev)**     | `http://localhost:8080`        | Puerto por defecto de Spring Boot.        |
| **Tecnología**         | Spring Boot 3.x, MySQL, REST/JSON |                                           |
| **Optimización**       | DTOs & Cloudinary              | Datos de salida limpios (sin recursividad). |

---

## 2. ENDPOINTS PRINCIPALES (Rutas de Consumo)

Todos los endpoints utilizan el método **GET** y responden con **código 200 OK**.

### 2.1. Artista y Biografía

| Endpoint               | Descripción                                                                 | Contrato de Salida |
|-------------------------|------------------------------------------------------------------------------|--------------------|
| `GET /artista`          | Perfil Completo. Devuelve el perfil del artista con todas sus colecciones anidadas (Discos, Videos, Biografía) como DTOs. | `ArtistaDTO`       |
| `GET /artista/biografia`| Contenido Biográfico. Devuelve solo el texto de la biografía y la URL de la imagen de fondo. | `BiografiaDTO`     |

### 2.2. Discografía

| Endpoint          | Descripción                                                       | Contrato de Salida     |
|-------------------|--------------------------------------------------------------------|------------------------|
| `GET /discos`     | Lista completa y ordenada de todos los álbumes.                   | `List<DiscoDTO>`       |
| `GET /discos/{id}`| Detalles de un álbum específico (Ej: `/discos/5`).                | `DiscoDTO`             |

### 2.3. Contenido Audiovisual

| Endpoint          | Descripción                                                       | Contrato de Salida     |
|-------------------|--------------------------------------------------------------------|------------------------|
| `GET /videos`     | Lista completa de videoclips y entrevistas.                       | `List<VideoDTO>`       |
| `GET /videos/{id}`| Detalles de un video específico (Ej: `/videos/12`).               | `VideoDTO`             |

---

## 3. CONTRATOS DE DATOS (Estructura JSON)

Esta es la estructura **JSON garantizada** de los objetos de respuesta.

---

### 🧩 A. ArtistaDTO  
**(Contrato Principal - `GET /artista`)**  
Este objeto es el contenedor raíz para la página principal.

```json
{
  "id": 1,
  "nombreReal": "Ricardo Horacio Iorio",
  "fotoPerfilPrincipal": "https://cloudinary.com/tu-cloud/iorio_perfil.webp",
  "biografia": { /* BiografiaDto */ },
  "discos": [
    { /* DiscoDto simplificado */ },
    { /* DiscoDto simplificado */ }
  ],
  "videos": [
    { /* VideoDto simplificado */ },
    { /* VideoDto simplificado */ }
  ]
}
```
### 💿 B. DiscoDto  
**(Contrato para `/discos`)**  
Respuesta optimizada para listas de álbumes.

```json
{
  "id": 5,
  "nombreDisco": "Ácido argentino",
  "listaTemas": "1. Robó un auto, 2. La revancha de América...",
  "imagenUrl": "https://cloudinary.com/tu-cloud/acido-argentino.webp",
  "artistaId": 1,
  "nombreArtista": "Ricardo Horacio Iorio"
}
```
### 🎬 C. VideoDto  
**(Contrato para `/videos`)**  
Contiene los enlaces directos a YouTube y la descripción.

```json
{
  "id": 12,
  "titulo": "Destrucción",
  "youtubeUrl": "https://youtu.be/ywCC5dxL5hs?si=...",
  "tipo": "Videoclip",
  "descripcion": "Tema correspondiente al álbum debut de V8...",
  "artistaId": 1,
  "nombreArtista": "Ricardo Horacio Iorio"
}
```
### 📜 D. BiografiaDto  
**(Contrato para `/artista/biografia`)**  
Contiene el contenido de texto largo sin datos adicionales.

```json
{
  "id": 1,
  "biografiaCompleta": "Ricardo Iorio (1962 – 2023) fue la voz fundamental y letrista icónico...",
  "imagenFondoUrl": "https://cloudinary.com/tu-cloud/iorio_fondo.webp",
  "artistaId": 1,
  "nombreArtista": "Ricardo Horacio Iorio"
}
```

