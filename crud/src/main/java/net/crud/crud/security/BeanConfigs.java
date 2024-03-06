package net.crud.crud.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@AllArgsConstructor
public class BeanConfigs {

    private final AutenticacaoService autenticacaoService;

    //se o autenticacaoService for criado depois do BeanConfig, deve ser passado como parâmetro
//    @Autowired
//    public void config(AuthenticationManagerBuilder auth,
//                       AutenticacaoService autenticacaoService) throws Exception {
//        auth.userDetailsService(autenticacaoService)
//                .passwordEncoder(passwordEncoder());
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //quando ele ver que foi necessário um authenticationManager ou um securityContext, ele vai chamar esses dois métodos
    //pode ser passada uma lista de daos no retorno do ProviderManager
    @Bean
    public AuthenticationManager authenticationManager()
            throws Exception {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
//        dao.setPasswordEncoder(new BCryptPasswordEncoder());
        dao.setUserDetailsService(autenticacaoService);
        return new ProviderManager(dao);
//        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityContextRepository securityContextRepository(){
        return new HttpSessionSecurityContextRepository();
    }

//    @Bean
//    public UserDetailsService userDetailsService(AutenticacaoService autenticacaoService){
//        return autenticacaoService;
//    }
}
