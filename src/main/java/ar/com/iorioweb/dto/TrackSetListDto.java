package ar.com.iorioweb.dto;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackSetListDto {
	
	private String tituloCancion;
    private String artista; 
    private String album;
    private String previewUrl; // El enlace MP3 de 30 segundos

}
