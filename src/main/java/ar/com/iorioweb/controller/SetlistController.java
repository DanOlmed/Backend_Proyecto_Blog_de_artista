package ar.com.iorioweb.controller;

import ar.com.iorioweb.dto.TrackSetListDto;
import ar.com.iorioweb.service.spotify.SpotifyApiService;
import ar.com.iorioweb.service.spotify.SpotifyAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/setlist")
public class SetlistController {

    @Autowired
    private SpotifyApiService spotifyApiService;
    @Autowired 
    private SpotifyAuthService authService;
    
    @GetMapping("/test-token")
    public ResponseEntity<String> testToken() {
        String token = authService.getAccessToken();
        if (token != null) {
            return ResponseEntity.ok("Token Obtenido: " + token.substring(0, 10) + "...");
        } else {
            return ResponseEntity.status(500).body("Error: Token es NULL");
        }
    }
    @GetMapping("/top30")
    public ResponseEntity<List<TrackSetListDto>> getTop30Setlist() {
        
        List<TrackSetListDto> setlist = new ArrayList<>();
        
        // =========================================================
        // 1. DEFINICIÓN DEL SETLIST (AÑADE LOS 30 TEMAS AQUÍ)
        // =========================================================
        setlist.add(new TrackSetListDto("Destrucción", "V8", "Luchando por el metal", null));
        setlist.add(new TrackSetListDto("Memoria de Siglos", "Hermética", "Ácido argentino", null));
        setlist.add(new TrackSetListDto("Sé Vos", "Almafuerte", "Almafuerte", null));
        setlist.add(new TrackSetListDto("Ayer Deseo, Hoy Realidad", "Ricardo Iorio", "Ayer deseo, hoy realidad", null));
        setlist.add(new TrackSetListDto("Gil trabajador", "Hermética", "Ácido argentino",null));
        setlist.add(new TrackSetListDto("Triunfo", "Almafuerte", "Almafuerte",null));
        setlist.add(new TrackSetListDto("Almafuerte", "Alamfuerte", "Almafuerte",null));
        setlist.add(new TrackSetListDto("Toro y pampa", "Almafuerte", "Toro y pampa",null));
        setlist.add(new TrackSetListDto("El pibe tigre", "Almafuerte", "Mundo guanaco",null));
        setlist.add(new TrackSetListDto("Allá en Tilcara", "Ricardo Iorio", "Peso argento",null));
        setlist.add(new TrackSetListDto("Mal bicho", "Ricardo Iorio", "Peso argento",null));
        setlist.add(new TrackSetListDto("Cacique Yatel", "Ricardo Iorio", "Peso argento",null));
        setlist.add(new TrackSetListDto("Mano Brava", "Almafuerte", "Almafuerte",null));
        setlist.add(new TrackSetListDto("Almafuerte", "Almafuerte", "Almafuerte",null));
        setlist.add(new TrackSetListDto("Homenaje", "Almafuerte", "A fondo blanco",null));
        setlist.add(new TrackSetListDto("Patria a hombro", "Almafuerte", "Ultimando",null));
        setlist.add(new TrackSetListDto("Robo un auto", "Ricardo Iorio", "Atesorando en los cielos",null));
        setlist.add(new TrackSetListDto("En las calles de liniers", "Ricardo Iorio", "Avivando la llama de la ley natural",null));
        setlist.add(new TrackSetListDto("Brigadas metálicas", "V8", "Luchando por el metal",null));
        setlist.add(new TrackSetListDto("Muy cansado estoy", "V8", "Luchando por el metal",null));
        setlist.add(new TrackSetListDto("Ideando la fuga", "V8", "Un paso mas en la batalla",null));
        setlist.add(new TrackSetListDto("Masa anestesiada", "Hermética", "Hermética",null));
        setlist.add(new TrackSetListDto("Cambalache", "Hermética", "Hermética",null));
        setlist.add(new TrackSetListDto("Robo un auto", "Hermética", "Ácido argentino",null));
        setlist.add(new TrackSetListDto("Buitres", "Almafuerte", "Mundo guanaco",null));
        setlist.add(new TrackSetListDto("Hombre peste", "Almafuerte", "Del entorno",null));
        setlist.add(new TrackSetListDto("A vos amigo", "Almafuerte", "A fondo blanco",null));
        setlist.add(new TrackSetListDto("Justo que te vas", "Ricardo Iorio", "Atesorando en los cielos",null));
        setlist.add(new TrackSetListDto("Sabe Don", "Ricardo Iorio", "Tangos y milongas",null));
        setlist.add(new TrackSetListDto("Orgullo argentino", "Almafuerte", "Piedra libre",null));
       
        
        // 2. Enriquecer los datos con la URL de Spotify (se hace la llamada a la API)
        for (TrackSetListDto track : setlist) {
            // Usamos el nombre de la canción y la banda/artista para la búsqueda
            String url = spotifyApiService.findTrackPreviewUrl(track.getTituloCancion(), track.getArtista());
            track.setPreviewUrl(url); 
        }
        
        return ResponseEntity.ok(setlist);
    }
}