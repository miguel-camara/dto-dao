package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS (Cross-Origin Resource Sharing) Configuration
 * Enables cross-origin requests from frontend applications
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configure CORS mappings
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins(
                "http://localhost:3000",      // React dev server
                "http://localhost:4200",      // Angular dev server
                "http://localhost:8080"       // Vue dev server
            )
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(3600);

        // Alternative: Allow all origins (use with caution in production)
        // registry.addMapping("/api/**")
        //     .allowedOrigins("*")
        //     .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
        //     .allowedHeaders("*")
        //     .maxAge(3600);
    }

    /*
     * Configuration can also be done via application.yml:
     * 
     * cors:
     *   allowedOrigins:
     *     - http://localhost:3000
     *     - http://localhost:4200
     *   allowedMethods: GET,POST,PUT,DELETE,OPTIONS,PATCH
     *   allowedHeaders: '*'
     *   allowCredentials: true
     *   maxAge: 3600
     */
}
