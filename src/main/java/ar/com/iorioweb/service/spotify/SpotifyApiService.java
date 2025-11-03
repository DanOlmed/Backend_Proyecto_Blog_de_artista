package ar.com.iorioweb.service.spotify;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import java.util.Map;
import java.net.URLEncoder; // Necesario para codificar la query
import java.nio.charset.StandardCharsets; // Necesario para la codificación

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
    	
    	
    	try {
            var response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            Map<String, Object> data = response.getBody();

            // ==========================================================
            // LOGGING PARA DEBUGGING EN RAILWAY
            // ==========================================================
            String logMessage = "Spotify Search (" + simpleQuery + ") | Status: " + response.getStatusCode();
            System.out.println(logMessage);

            if (data != null) {
                // Imprimir una porción de la respuesta para confirmar que la canción fue encontrada
                System.out.println("Spotify Response Body start: " + data.toString().substring(0, Math.min(data.toString().length(), 500)) + "...");
            }
            // ==========================================================
            
            // Navegar el JSON: tracks -> items[0] -> preview_url
            if (data != null && data.containsKey("tracks")) {
                Map<String, Object> tracks = (Map<String, Object>) data.get("tracks");
                java.util.List<Map<String, Object>> items = (java.util.List<Map<String, Object>>) tracks.get("items");
                
                if (!items.isEmpty()) {
                    // La canción fue encontrada. Verificamos la URL.
                    if (items.get(0).containsKey("preview_url")) {
                        String previewUrl = (String) items.get(0).get("preview_url");
                        
                        if (previewUrl != null) {
                            System.out.println("Spotify SUCCESS: Preview URL encontrado.");
                            return previewUrl;
                        } else {
                            // Este es el caso que te está sucediendo
                            System.out.println("Spotify FAIL: Canción encontrada, pero 'preview_url' es NULO en el objeto de Spotify.");
                        }
                    }
                } else {
                    System.out.println("Spotify FAIL: No se encontraron resultados para la query.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error al buscar en Spotify para " + simpleQuery + ": " + e.getMessage());
        }
        return null;
    }
        String token = authService.getAccessToken();
        if (token == null) return null;

        // 1. CONSTRUCCIÓN DE LA QUERY: USAMOS UNA BÚSQUEDA MÁS AMPLIA Y SIMPLE
        //    Ej: "Destrucción V8" (funciona mejor que 'Destrucción artist:"V8"')
        String simpleQuery = trackName + " " + artistName;
        
        // Codificamos la query para URL (manejando espacios y caracteres especiales)
        String encodedQuery = URLEncoder.encode(simpleQuery, StandardCharsets.UTF_8);
        
        // 2. CONSTRUIR LA URL
        String url = UriComponentsBuilder.fromHttpUrl(searchUrl)
            .queryParam("q", encodedQuery) // Usamos la query codificada
            .queryParam("type", "track")
            .queryParam("limit", 1) 
            .queryParam("market", "AR")
            .toUriString();
        
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            var response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            Map<String, Object> data = response.getBody();

            // 3. NAVEGACIÓN DEL JSON
            if (data != null && data.containsKey("tracks")) {
                Map<String, Object> tracks = (Map<String, Object>) data.get("tracks");
                java.util.List<Map<String, Object>> items = (java.util.List<Map<String, Object>>) tracks.get("items");
                
                if (!items.isEmpty() && items.get(0).containsKey("preview_url")) {
                    return (String) items.get(0).get("preview_url");
                }
            }
        } catch (Exception e) {
            // Imprimir el error para debuggear en Railway
            System.err.println("Error al buscar en Spotify para " + simpleQuery + ": " + e.getMessage());
        }
        return null; // Devuelve null si no encuentra la URL
    }
}