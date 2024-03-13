package net.crud.crud.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import net.crud.crud.entities.UserLogin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
//    private final SecurityContextRepository securityContextRepository;
    private final CookieUtil cookieUtil = new CookieUtil();

    @PostMapping("/auth/login")
    ResponseEntity<String> authenticate(@RequestBody UserLogin userLogin,
                                       HttpServletResponse httpServletResponse){
        try{
            //many classes implements authentication, like authenticationManager...
            //the token class converts any type of object to a token : principal -> username, credentials -> password
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                    (userLogin.getUsername(), userLogin.getPassword());

            //authManager sends the token to authentication
            //if the object is authenticated it'll be saved into context or generate a cookie
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            //se ele conseguir se autenticar, não é mais username, mas sim userDetails

            //the following code keep the user data saved
            //create a new context if the user is authenticated
            //it is needed to keep the authenticated user in the system
//            SecurityContext context = SecurityContextHolder.createEmptyContext();
//            context.setAuthentication(authentication);
//            securityContextRepository.saveContext(context, httpServletRequest, httpServletResponse);

            //gera um cookie através da classe cookieUtil e armazena na resposta
            //o navegador vai armazenar esse cookie
            //criação de um token e retorno para o usuário a partir do momento em que ele está autenticado
            UserDetails user = (UserDetails) authentication.getPrincipal();
            Cookie cookie = cookieUtil.cookieGeneratorJwt(user);
            httpServletResponse.addCookie(cookie);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Requisição bem sucedida!");
        } catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro na requisição");
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = null;
        try {
            cookie = cookieUtil.getCookie(request, "JWT");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        } catch (Exception e) {
            response.setStatus(401);
        }

    }

}
