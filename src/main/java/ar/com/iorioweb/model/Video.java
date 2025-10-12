package ar.com.iorioweb.model;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Data
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String youtubeUrl; // o vimeoUrl, etc.
    private String descripcion;
    
    // Opcional: para saber a qué disco o canción está asociado
    @ManyToOne
    @JoinColumn(name = "disco_id", nullable = true)
    private Disco disco;
    
    // se puede agregar una imagen de miniatura si no la obtenemos de la API del video
    private String thumbnailUrl; 
    @ManyToOne
    @JoinColumn(name = "artista_id") // Esta columna estará en la tabla Video
    private Artista artista;
    
}