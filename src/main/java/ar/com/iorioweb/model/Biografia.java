package ar.com.iorioweb.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Biografia {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Texto largo
    @Column(columnDefinition = "TEXT") // Usar TEXT o LONGTEXT para textos muy largos en MySQL
    private String biografiaCompleta;
    
    // Texto corto para una introducción o card
    @Column(columnDefinition = "VARCHAR(500)")
    private String introduccionCorta;

    // URL de una imagen de fondo o de galería general
    private String imagenFondoUrl; 
    private String imagenCard1Url;
    
    // Relación inversa para vincular con el Artista
    @OneToOne
    @JoinColumn(name = "artista_id")
    private Artista artista; 
    
}