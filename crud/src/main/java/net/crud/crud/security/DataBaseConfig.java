package net.crud.crud.security;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import net.crud.crud.entities.User;
import net.crud.crud.repositories.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
@AllArgsConstructor
public class DataBaseConfig {

    private final UserRepository userRepository;

    @PostConstruct
    public void init(){
        User user = new User();
        user.setEmail("teste@teste.com");
        //builder age como construtor
        user.setUsuarioDetailsEntity(
                UsuarioDetailsEntity.builder()
                        .user(user)
                        .enabled(true)
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .username("ana4")
                        .password(new BCryptPasswordEncoder().encode("senha123"))
                        .authorities(List.of(Authorities.GET, Authorities.POST))
                        .build());

        userRepository.save(user);
    }
}
