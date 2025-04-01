package ifsul.io.IFMeet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // ✅ Permite múltiplas origens (adicione outras se necessário)
        config.setAllowedOrigins(Arrays.asList(
                "https://ifmeet.sytes.net",
                "http://localhost:8081"  // Exemplo de frontend local
        ));

        // ✅ Métodos HTTP permitidos
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // ✅ Cabeçalhos permitidos
        config.setAllowedHeaders(Arrays.asList(
                "Authorization", "Content-Type", "Accept", "Content-Disposition", "x-usuario-pessoa-id"
        ));

        // ✅ Expor cabeçalhos necessários
        config.setExposedHeaders(Arrays.asList("Content-Disposition"));

        // ✅ Permitir credenciais (Importante para autenticação)
        config.setAllowCredentials(true);

        // ✅ Tempo de cache do CORS (1 hora)
        config.setMaxAge(3600L);

        // ✅ Aplica configuração para todas as rotas
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}

