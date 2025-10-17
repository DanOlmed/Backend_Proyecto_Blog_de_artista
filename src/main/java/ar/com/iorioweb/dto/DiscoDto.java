package ar.com.iorioweb.dto;
import lombok.*;



//se utiliza la creacion de data transfer object para evitar bucles recursivos cuando hacemos la peticion y determinado objeto (disco en este caso, traiga por cada artista cada disco que tiene adentro de la misma clase)
@Data
public class DiscoDto {
	private Long id;
    private String nombreDisco;
    private String listaTemas; // Se mantiene como TEXT
    private String imagenUrl;
    
    // Solo incluimos la información MÍNIMA del artista
    private Long artistaId;
    private String nombreArtista;

}
