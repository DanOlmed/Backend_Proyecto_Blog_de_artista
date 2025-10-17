package ar.com.iorioweb.dto;

import lombok.Data;

@Data
public class VideoDto {
    private Long id;
    private String titulo;
    private String youtubeUrl;
    private String descripcion;
    
    // Información mínima del Artista y Disco
    private Long artistaId;
    private String nombreArtista;
    private Long discoId; // Si existe la FK a Disco en tu modelo
}