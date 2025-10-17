package ar.com.iorioweb.controller;
import ar.com.iorioweb.dto.ArtistaDto; 
import ar.com.iorioweb.dto.BiografiaDto;         
import ar.com.iorioweb.service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artista") 
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    // ENDPOINT: GET /artista
    @GetMapping
    public ResponseEntity<ArtistaDto> obtenerArtistaPrincipal() { 
        ArtistaDto dto = artistaService.obtenerArtistaPrincipalDTO(); 
        
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
    
    // ENDPOINT: GET /artista/biografia
    @GetMapping("/biografia")
    public ResponseEntity<BiografiaDto> obtenerBiografia() { 
        BiografiaDto dto = artistaService.obtenerBiografiaDTO(); 
        
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
}