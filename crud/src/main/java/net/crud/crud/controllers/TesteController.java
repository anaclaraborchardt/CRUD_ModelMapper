package net.crud.crud.controllers;

import lombok.AllArgsConstructor;
import net.crud.crud.models.entities.User;
import net.crud.crud.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<User> users(){
        Pageable pageable =
                PageRequest.of(0,20, Sort.Direction.ASC, "email");

        return userRepository.findAll(pageable);
    }

    @GetMapping("/users2")
    public Page<User> users2(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    @GetMapping("/users3")
    public Page<User> users3(String email, Pageable pageable){
        //containing é para quando não é só retorno de igualdades
        //ex: pesquiso por ana e aparece todas as anas derivadas
        return userRepository.findUserByEmailContaining(email, pageable);
    }
}
