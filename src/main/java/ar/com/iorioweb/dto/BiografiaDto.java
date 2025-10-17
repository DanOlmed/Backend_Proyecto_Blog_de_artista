package ar.com.iorioweb.dto;
import lombok.Data;

@Data
public class BiografiaDto {
    private Long id;
    private String biografiaCompleta;
    private String imagenFondoUrl;
    
    // Solo se necesita el ID del artista para referencia
    private Long artistaId;
    private String nombreArtista;
}