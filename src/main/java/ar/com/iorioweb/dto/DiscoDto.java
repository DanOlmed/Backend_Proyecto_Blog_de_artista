package ar.com.iorioweb.dto;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;



//se utiliza la creacion de data transfer object para evitar bucles recursivos cuando hacemos la peticion y determinado objeto (disco en este caso, traiga por cada artista cada disco que tiene adentro de la misma clase)
@Data
public class DiscoDto {
	@Schema(description = "ID del álbum en la base de datos.", example = "5")
	private Long id;
	@Schema(description = "Nombre oficial del disco.", example = "Ácido argentino", required = true)
    private String nombreDisco;
	@Schema(description = "Lista de temas del sico.", example = "1. Robo un auto, 2. Gil trabajador...", required = true)
    private String listaTemas; // Se mantiene como TEXT
	@Schema(description = "Arte de tapa del disco.", example = "https://res.cloudinary.com/dpytht2dn/image/upload/v1760309255/hermeticaAcidoArgentino_k4tyoe.webp", required = true)
    private String imagenUrl;
    
    // Solo incluimos la información MÍNIMA del artista
	
    private Long artistaId;
    @Schema(description = "Nombre completo del artista.", example = "Ricardo Horacio Iorio", required = true)
    private String nombreArtista;

}
