package net.crud.crud.security.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import net.crud.crud.models.entities.User;
import net.crud.crud.repositories.UserRepository;
import net.crud.crud.security.model.Authorities;
import net.crud.crud.security.model.UsuarioDetailsEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
@AllArgsConstructor
public class DataBaseConfig {

    private final UserRepository userRepository;

    @PostConstruct
    public void init(){
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setEmail("teste@teste.com" + i);
            //builder age como construtor
            user.setUsuarioDetailsEntity(
                    UsuarioDetailsEntity.builder()
                            .user(user)
                            .enabled(true)
                            .accountNonExpired(true)
                            .accountNonLocked(true)
                            .credentialsNonExpired(true)
                            .username("ana"+ i)
                            .password(new BCryptPasswordEncoder().encode("senha123"+ i))
                            .authorities(List.of(Authorities.GET, Authorities.POST))
                            .build());

            userRepository.save(user);
        }
    }
}
