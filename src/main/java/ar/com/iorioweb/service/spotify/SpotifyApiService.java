package ar.com.iorioweb.service.spotify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List; // Necesario para List
import java.util.Map; // Necesario para Map
import java.util.Objects; // Necesario si usas Objects.requireNonNull() o similar

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

        String simpleQuery = trackName + " " + artistName;
        String encodedQuery = URLEncoder.encode(simpleQuery, StandardCharsets.UTF_8);
        
        // CONSTRUCCIÓN DE LA URL CON MARKET=AR
        String url = UriComponentsBuilder.fromHttpUrl(searchUrl)
            .queryParam("q", encodedQuery) 
            .queryParam("type", "track")
            .queryParam("limit", 1) 
            .queryParam("market", "AR") // Buscamos en Argentina
            .toUriString();
        
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // Utilizamos Objects.requireNonNull para asegurar que la respuesta no sea null
            var response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            Map<String, Object> data = Objects.requireNonNull(response.getBody()); 

            // ==========================================================
            // LOGGING PARA DEBUGGING EN RAILWAY
            // ==========================================================
            String logMessage = "Spotify Search (" + simpleQuery + ") | Status: " + response.getStatusCode();
            System.out.println(logMessage);

            if (data != null) {
                // Imprimir una porción de la respuesta (usando Math.min de forma segura)
                String dataString = data.toString();
                System.out.println("Spotify Response Body start: " + dataString.substring(0, Math.min(dataString.length(), 500)) + "...");
            }
            // ==========================================================
            
            // Navegar el JSON: tracks -> items[0] -> preview_url
            if (data.containsKey("tracks")) {
                Map<String, Object> tracks = (Map<String, Object>) data.get("tracks");
                
                // Aseguramos que estamos trabajando con List de Map
                if (tracks.containsKey("items")) {
                     List<Map<String, Object>> items = (List<Map<String, Object>>) tracks.get("items");
                
                    if (!items.isEmpty()) {
                        // La canción fue encontrada. Verificamos la URL.
                        if (items.get(0).containsKey("preview_url")) {
                            String previewUrl = (String) items.get(0).get("preview_url");
                            
                            if (previewUrl != null) {
                                System.out.println("Spotify SUCCESS: Preview URL encontrado.");
                                return previewUrl;
                            } else {
                                System.out.println("Spotify FAIL: Canción encontrada, pero 'preview_url' es NULO en el objeto de Spotify.");
                            }
                        }
                    } else {
                        System.out.println("Spotify FAIL: No se encontraron resultados para la query.");
                    }
                }
            }
        } catch (Exception e) {
            // Imprimir el error
            System.err.println("ERROR al buscar en Spotify: " + e.getMessage());
        }
        return null;
    }
}