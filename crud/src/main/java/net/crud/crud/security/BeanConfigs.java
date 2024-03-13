package net.crud.crud.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@AllArgsConstructor
public class BeanConfigs {

    private final AuthenticationService autenticacaoService;

    //quando ele ver que foi necessário um authenticationManager ou um securityContext, ele vai chamar esses dois métodos
    //pode ser passada uma lista de daos no retorno do ProviderManager
    @Bean
    public AuthenticationManager authenticationManager()
            throws Exception {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(new BCryptPasswordEncoder());
        dao.setUserDetailsService(autenticacaoService);
        return new ProviderManager(dao);
    }

    @Bean
    public SecurityContextRepository securityContextRepository(){
        return new HttpSessionSecurityContextRepository();
    }


    @Bean
    public CorsConfigurationSource corsConfig(){
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(List.of("http://localhost:3000"));
        cors.setAllowedMethods(List.of("POST", "GET"));
        //allow taking the client cookies
        cors.setAllowCredentials(true);
        //headers of the requests, with "*" means that can be any header
        cors.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource corsConfigurationSource =
                new UrlBasedCorsConfigurationSource();
        //registering the personalization of settings
        //"/**" = all paths are allowed - define visibility of cookies
        corsConfigurationSource.registerCorsConfiguration("/**", cors);

        return corsConfigurationSource;

    }
}
