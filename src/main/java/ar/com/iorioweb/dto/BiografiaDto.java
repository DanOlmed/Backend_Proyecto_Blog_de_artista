package ar.com.iorioweb.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BiografiaDto {
	@Schema(description = "Id de la biografia.", example = "1")
    private Long id;
	@Schema(description = "Biografia completa del artista.", example = "Ricardo Horacio Iorio nació en ....")
    private String biografiaCompleta;
	@Schema(description = "Url de una magen para utilizar en una card.", example = "https://res.cloudinary.com/dpytht2dn/image/upload/v1760309127/iorio_bajo_juventud_gsnzzf.jpg")
    private String imagenFondoUrl;
    
    // Solo se necesita el ID del artista para referencia
    private Long artistaId;
    @Schema(description = "Nombre completo del artista.", example = "Ricardo Horacio Iorio")
    private String nombreArtista;
}