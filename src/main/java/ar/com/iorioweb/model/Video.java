package ar.com.iorioweb.model;
import jakarta.persistence.*;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String youtubeUrl; // o vimeoUrl, etc.
    private String descripcion;
    
    // Opcional: para saber a qué disco o canción está asociado
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "disco_id", nullable = true)
    private Disco disco;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "artista_id") // Esta columna estará en la tabla Video
    private Artista artista;
    
}