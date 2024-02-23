package net.crud.crud.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.crud.crud.entities.UserLogin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    @PostMapping("/login")
    ResponseEntity<String> authenticate(@RequestBody UserLogin userLogin,
                                       HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse){
        try{
            //many classes implements authentication, like authenticationManager...
            //the token class converts any type of object to a token : principal -> username, credentials -> password
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                    (userLogin.getUsername(), userLogin.getPassword());

            //authManager sends the token to authentication
            //if the object is authenticated it'll be saved into context
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            //the following code keep the user data saved
            //create a new context if the user is authenticated
            //it is needed to keep the authenticated user in the system
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            securityContextRepository.saveContext(context, httpServletRequest, httpServletResponse);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("ok");
        } catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("não");
        }
    }

}
