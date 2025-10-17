package ar.com.iorioweb.model;
import jakarta.persistence.*;

import lombok.*;

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
    private String listaTemas;
    private String imagenUrl;
    private String anio;
    @ManyToOne
    @JoinColumn(name = "artista_id") 
    private Artista artista;
    
	
	    
    
}
