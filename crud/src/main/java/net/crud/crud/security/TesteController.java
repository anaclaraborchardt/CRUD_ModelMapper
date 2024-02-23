package net.crud.crud.security;

import lombok.AllArgsConstructor;
import net.crud.crud.entities.User;
import net.crud.crud.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teste")
@AllArgsConstructor
public class TesteController {

    private final UserRepository userRepository;

    //401 - Sem autenticação
    //403 - Sem permissão para acessar determinado conteúdo

    @GetMapping
    public String teste(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "teste " + auth.getName();
    }

    @PostMapping
    public void cadastroUsuario(@RequestBody User user){
        userRepository.save(user);
    }

    @GetMapping("/users")
    public List<User> users(){
        return userRepository.findAll();
    }
}
