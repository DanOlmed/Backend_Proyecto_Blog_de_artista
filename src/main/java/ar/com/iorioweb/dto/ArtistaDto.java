package ar.com.iorioweb.dto;

import lombok.*;
import java.util.List;

@Data
public class ArtistaDto {
    private Long id;
    private String nombreReal;
    private String fotoPerfilPrincipal;
    
    // Incluye solo las IDs o DTOs simples para las colecciones
    private BiografiaDto biografia; 
    private List<DiscoDto> discos; 
    private List<VideoDto> videos; 
}