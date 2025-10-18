package ar.com.iorioweb.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("IORIO Web Backend API")
                .version("1.0")
                .description("API REST del proyecto IORIO Web — Proporciona acceso a datos biográficos, discografía y contenido audiovisual de Ricardo Iorio.")
                .contact(new Contact()
                    .name("Daniel Olmedo")
                    .email("oxdeo85@gmail.com")
                    .url("https://github.com/DanOlmed"))
                .license(new License()
                    .name("MIT License")
                    .url("https://opensource.org/licenses/MIT"))
            );
    }
}