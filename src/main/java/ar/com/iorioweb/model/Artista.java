package ar.com.iorioweb.model;
import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.*;
import 	lombok.*;

@Entity
@Data
public class Artista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombreReal;
	//imagen principal para el logo o para fondo de la pagina
	private String fotoPerfilPrincipal;
	@OneToOne(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
	private Biografia biografia;
	@OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Disco> discos = new ArrayList<>();
	@OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Video> videos = new ArrayList<>();
	
	

}
