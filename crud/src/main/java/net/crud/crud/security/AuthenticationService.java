package net.crud.crud.security;

import lombok.AllArgsConstructor;
import net.crud.crud.entities.User;
import net.crud.crud.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;

    //faz a consulta de um usuário e verifica sua existência
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsuarioDetailsEntity_Username(username);

        if(userOptional.isPresent()){
            return (userOptional).get().getUsuarioDetailsEntity();
        }
        throw new UsernameNotFoundException("Dados inválidos");
    }
}
