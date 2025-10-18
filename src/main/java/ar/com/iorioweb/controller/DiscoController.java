package ar.com.iorioweb.controller;
import io.swagger.v3.oas.annotations.tags.Tag; // <-- Importación Swagger
import io.swagger.v3.oas.annotations.Operation; // <-- Importación Swagger

import ar.com.iorioweb.model.Disco;
import ar.com.iorioweb.service.DiscoService;
import ar.com.iorioweb.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Indica que es un componente REST que maneja JSON
@RequestMapping("/discos") // Prefijo base: http://localhost:8080/api/discos
@Tag(name = "Discografía", description = "Gestión de los álbumes y listas de temas.")
public class DiscoController {

    @Autowired // Inyectamos la interfaz del Servicio
    private DiscoService discoService;

    // ENDPOINT: GET /api/discos (Obtener todos los discos)
    @Operation(summary = "Obtiene la lista completa de todos los discos.")
    @GetMapping
    public ResponseEntity<List<DiscoDto>> obtenerDiscos() {
        List<DiscoDto> discos = discoService.obtenerTodosLosDiscos();
        return ResponseEntity.ok(discos); // Devuelve HTTP 200 OK
    }

    // ENDPOINT: GET /api/discos/{id} (Obtener un disco por su ID)
    @Operation(summary = "Obtiene disco por id.")
    @GetMapping("/{id}")
    public ResponseEntity<DiscoDto> obtenerDiscoPorId(@PathVariable Long id) {
        DiscoDto disco = discoService.obtenerDiscoPorId(id);
        
        if (disco != null) {
            return ResponseEntity.ok(disco); // Devuelve 200 OK y el disco
        } else {
            return ResponseEntity.notFound().build(); // Devuelve 404 Not Found
        }
    }
}