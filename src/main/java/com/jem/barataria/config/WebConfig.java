package com.jem.barataria.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @SuppressWarnings("null")
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configuración de CORS
        registry.addMapping("/**") // Permite CORS para todas las rutas
                .allowedOrigins("http://localhost:4200") // Añade aquí el origen de tu cliente Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*") // Permite todos los headers
                .allowCredentials(true); // Si necesitas enviar cookies o headers de autenticación
    }
}
