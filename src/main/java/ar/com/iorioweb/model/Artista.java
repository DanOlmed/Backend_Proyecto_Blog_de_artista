package ar.com.iorioweb.model;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder // <--- 1. Usar el patrón Builder
@NoArgsConstructor // <--- 2. Necesario para JPA
@AllArgsConstructor
public class Artista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombreReal;
	//imagen principal para el logo o para fondo de la pagina
	private String fotoPerfilPrincipal;
	@OneToOne(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
	private Biografia biografia;
	@Builder.Default // Inicializa la lista por defecto
    @ToString.Exclude
    @JsonManagedReference
	@OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Disco> discos = new ArrayList<>();
	@Builder.Default // Inicializa la lista por defecto
    @ToString.Exclude
    @JsonManagedReference
	@OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Video> videos = new ArrayList<>();
	
	

}
