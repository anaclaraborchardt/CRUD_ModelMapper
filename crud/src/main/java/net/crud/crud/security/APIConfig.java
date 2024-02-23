package net.crud.crud.security;

import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextRepository;

import java.util.List;

@Configuration
@AllArgsConstructor
public class APIConfig {

    private final SecurityContextRepository securityContextRepository;

    //hasAuthority - uma autorização
    //hasAnyAuthority: possui mais de uma autorização
    //requestMatchers: sobrecarga de métodos: aceita tanto as rotas, quanto os métodos http
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //quando o csrf estiver habilitado, ele vai gerar um token,
        // vai retornar esse token e deve ser enviado junto em formulários
        // para o backEnd - isso é necessário em requisições: post, patch, delete...
        //
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.authorizeHttpRequests(authorizeRequest ->
                authorizeRequest
                        .requestMatchers(HttpMethod.GET, "/teste").permitAll()
                        .requestMatchers(HttpMethod.GET, "/teste/users").permitAll()
                .anyRequest().authenticated());
        httpSecurity.securityContext((context) -> context.securityContextRepository(securityContextRepository));
        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.logout(Customizer.withDefaults());
        return httpSecurity.build();
    }


    //in memory user authentication

//    @Bean
//    public UserDetailsService inMemoryUser(){
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("ana")
//                .password("ana")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}
