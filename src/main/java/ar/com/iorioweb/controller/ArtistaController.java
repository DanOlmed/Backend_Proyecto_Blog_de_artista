package ar.com.iorioweb.controller;
import ar.com.iorioweb.dto.ArtistaDto; 
import io.swagger.v3.oas.annotations.tags.Tag; // <-- Importación Swagger
import io.swagger.v3.oas.annotations.Operation; // <-- Importación Swagger
import ar.com.iorioweb.dto.BiografiaDto;         
import ar.com.iorioweb.service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artista") 
@Tag(name = "Artista", description = "Gestión de los datos e informacion del artista.") // <-- Título de la sección
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    // ENDPOINT: GET /artista
    @Operation(summary = "Obtiene la informacion completa del artista.") // <-- Descripción en el gráfico
    @GetMapping
    public ResponseEntity<ArtistaDto> obtenerArtistaPrincipal() { 
        ArtistaDto dto = artistaService.obtenerArtistaPrincipalDTO(); 
        
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
    
    // ENDPOINT: GET /artista/biografia
    @Operation(summary = "Obtiene la biografia completa del artista.")
    @GetMapping("/biografia")
    public ResponseEntity<BiografiaDto> obtenerBiografia() { 
        BiografiaDto dto = artistaService.obtenerBiografiaDTO(); 
        
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
}