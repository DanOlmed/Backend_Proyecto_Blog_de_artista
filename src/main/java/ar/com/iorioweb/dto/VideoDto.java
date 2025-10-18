package ar.com.iorioweb.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class VideoDto {
	@Schema(description = "ID del video.", example = "1")
    private Long id;
	@Schema(description = "Titulo del videoclip.", example = "Destrucción-V8")
    private String titulo;
	@Schema(description = "Enlace del video en yourtube.", example = "https://youtu.be/ywCC5dxL5hs?si=OcllFKDQF5yCGYAF")
    private String youtubeUrl;
	@Schema(description = "Descripcion del videoclip.", example = "Tema del primer disco de V8")
    private String descripcion;
    
    // Información mínima del Artista y Disco
	
    private Long artistaId;
    @Schema(description = "Nombre completo del artista.", example = "Ricardo Horacio Iorio", required = true)
    private String nombreArtista;
    @Schema(description = "Id del disco en el cual el tema aparece.", example = "1")
    private Long discoId; // Si existe la FK a Disco en tu modelo
}