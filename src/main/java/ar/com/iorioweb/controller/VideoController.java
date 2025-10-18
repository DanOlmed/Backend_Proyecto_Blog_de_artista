package ar.com.iorioweb.controller;
import ar.com.iorioweb.dto.VideoDto; // <-- CAMBIO

import ar.com.iorioweb.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag; // <-- Importación Swagger
import io.swagger.v3.oas.annotations.Operation; // <-- Importación Swagger
@RestController
@RequestMapping("/videos") 
@Tag(name = "Videos", description = "Gestión de videoclips musicales y entrevistas.") // <-- Título de la sección
public class VideoController {

    @Autowired
    private VideoService videoService;

    // ENDPOINT: GET /videos
    @Operation(summary = "Obtiene la lista completa de todos los videos.")
    @GetMapping
    public ResponseEntity<List<VideoDto>> obtenerVideos(@RequestParam(required = false) String tipo) { // <-- CAMBIO
        List<VideoDto> videos; // <-- CAMBIO
        
        if (tipo != null && !tipo.isEmpty()) {
            videos = videoService.obtenerVideosPorTipo(tipo); 
        } else {
            videos = videoService.obtenerTodosLosVideos();
        }
        
        return ResponseEntity.ok(videos);
    }

    // ENDPOINT: GET /videos/{id}
    @Operation(summary = "Obtiene un video determinado por id.")
    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> obtenerVideoPorId(@PathVariable Long id) { // <-- CAMBIO
        VideoDto dto = videoService.obtenerVideoPorId(id); // <-- CAMBIO
        
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
}