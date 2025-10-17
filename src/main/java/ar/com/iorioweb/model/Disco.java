package ar.com.iorioweb.model;
import jakarta.persistence.*;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Disco {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreDisco;
    @Column(columnDefinition = "TEXT")
    private String listaTemas;
    private String imagenUrl;
    private String anio;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "artista_id") 
    private Artista artista;
    
	
	    
    
}
