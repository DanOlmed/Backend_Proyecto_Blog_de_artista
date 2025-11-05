package ar.com.iorioweb.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica la configuración a TODOS los endpoints: /artista, /discos, /videos, etc.
            
            // 🚨 Dominio de Vercel y un puerto local común para desarrollo (opcional)
            .allowedOrigins(
                "*" // permite cualquier origen, no es recomendado pero en este caso por el momento es útil usarlo así
            ) 
            
            // Métodos HTTP que el frontend puede usar
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") 
            
            // Permite todos los encabezados (headers)
            .allowedHeaders("*"); 
    }
}