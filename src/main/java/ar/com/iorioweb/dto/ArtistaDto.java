package ar.com.iorioweb.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.List;

@Data
public class ArtistaDto {
	@Schema(description = "Id del artista.", example = "1")
    private Long id;
	@Schema(description = "Nombre real del artista.", example = "Ricardo Horacio Iorio")
    private String nombreReal;
	@Schema(description = "Url de la imagen principal para fondo/backgroun/cuadro", example = "https://res.cloudinary.com/dpytht2dn/image/upload/v1760309127/iorio_biaga9.jpg")
    private String fotoPerfilPrincipal;
    
    // Incluye solo las IDs o DTOs simples para las colecciones
	@Schema(description = "Biografia enlazada del artista", example = "Ricardo Horacio Iorio ...")
    private BiografiaDto biografia; 
	@Schema(description = "Lista de discos del artista.", example = "Luchando por el metal, A fondo blanco...")
    private List<DiscoDto> discos; 
	@Schema(description = "Lista de videos del artista.", example = "Destruccion, El pibe tigre, etc")
    private List<VideoDto> videos; 
}