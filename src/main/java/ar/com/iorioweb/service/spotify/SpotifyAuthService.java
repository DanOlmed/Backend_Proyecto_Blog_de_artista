package ar.com.iorioweb.service.spotify;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import java.util.Base64;
import java.util.Map;

@Service
public class SpotifyAuthService {

    @Value("${spotify.client.id}")
    private String clientId;
    @Value("${spotify.client.secret}")
    private String clientSecret;
    @Value("${spotify.auth.url}")
    private String authUrl;

    private String accessToken;
    private long tokenExpiryTime;

    public String getAccessToken() {
        // Le damos 5 min de margen antes de la expiración real
        if (accessToken == null || System.currentTimeMillis() > tokenExpiryTime - 300000) { 
            
            String authString = clientId + ":" + clientSecret;
            String encodedAuth = Base64.getEncoder().encodeToString(authString.getBytes());

            RestTemplate restTemplate = new RestTemplate();
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Basic " + encodedAuth);
            headers.set("Content-Type", "application/x-www-form-urlencoded");
            
            String body = "grant_type=client_credentials";
            HttpEntity<String> request = new HttpEntity<>(body, headers);

            try {
                // Endpoint para obtener el token
                var response = restTemplate.postForEntity(authUrl, request, Map.class);
                
                if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                    Map<String, Object> data = response.getBody();
                    accessToken = (String) data.get("access_token");
                    // Convertir segundos a milisegundos y establecer la expiración
                    Integer expiresIn = (Integer) data.get("expires_in");
                    tokenExpiryTime = System.currentTimeMillis() + (expiresIn * 1000L); 
                }
            } catch (Exception e) {
                System.err.println("Error al obtener token de Spotify: " + e.getMessage());
                return null;
            }
        }
        return accessToken;
    }
}