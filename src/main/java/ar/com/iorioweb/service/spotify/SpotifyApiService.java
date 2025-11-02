package ar.com.iorioweb.service.spotify;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import java.util.Map;

@Service
public class SpotifyApiService {
    
    @Value("${spotify.search.url}")
    private String searchUrl;
    
    // Inyectamos el servicio que maneja el token
    private final SpotifyAuthService authService; 

    public SpotifyApiService(SpotifyAuthService authService) {
        this.authService = authService;
    }

    public String findTrackPreviewUrl(String trackName, String artistName) {
        String token = authService.getAccessToken();
        if (token == null) return null;

        // Construir la query de búsqueda: "nombre_cancion artist:nombre_artista"
        String query = trackName + " artist:\"" + artistName + "\"";
        
        // Construir la URL completa: /v1/search?q=query&type=track&limit=1
        String url = UriComponentsBuilder.fromHttpUrl(searchUrl)
            .queryParam("q", query)
            .queryParam("type", "track")
            .queryParam("limit", 1) // Solo el mejor resultado
            .toUriString();
        
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            var response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            Map<String, Object> data = response.getBody();

            // Navegar el JSON: tracks -> items[0] -> preview_url
            if (data != null && data.containsKey("tracks")) {
                Map<String, Object> tracks = (Map<String, Object>) data.get("tracks");
                java.util.List<Map<String, Object>> items = (java.util.List<Map<String, Object>>) tracks.get("items");
                
                if (!items.isEmpty() && items.get(0).containsKey("preview_url")) {
                    return (String) items.get(0).get("preview_url");
                }
            }
        } catch (Exception e) {
            System.err.println("Error al buscar en Spotify: " + e.getMessage());
        }
        return null;
    }
}