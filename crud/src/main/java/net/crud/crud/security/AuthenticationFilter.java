package net.crud.crud.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final CookieUtil cookieUtil = new CookieUtil();
    private final JwtUtil jwtUtil = new JwtUtil();
    private final SecurityContextRepository securityContextRepository;
    //a authenticationService só vai ser utilizada se o subject do jwt for
    //um atributo autenticado, como email, username, cpf...
    //caso fosse um id, por exemplo, poderia só chamar a repository ou a service
    private final AuthenticationService userDetailsService;

    @Override
    protected void doFilterInternal
            (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //search the token and validate it
        Cookie cookie = cookieUtil.getCookie(request, "JWT");
        String token = cookie.getValue();
        String username = jwtUtil.getUsername(token);

        //create an authenticated user
        UserDetails user = userDetailsService.loadUserByUsername(username);
        Authentication auth =
                new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());

        //save the authenticated user in securityContext
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        securityContextRepository.saveContext(context, request, response);

        //Continuation of the request
        filterChain.doFilter(request,response);
    }

    //Verifies if the user needs to be authenticated
    private boolean publicRoute(HttpServletRequest request){
        return request.getRequestURI().equals("/login") &&
                request.getMethod().equals("POST");
    }
}
