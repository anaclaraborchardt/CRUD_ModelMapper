package net.crud.crud.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.util.WebUtils;

//cookies settings
public class CookieUtil {

    public Cookie cookieGeneratorJwt(UserDetails userDetails){
        String token = new JwtUtil().generateToken(userDetails);
        Cookie cookie = new Cookie("JWT", token);
        cookie.setPath("/");
        //depois do tempo passado, o cookie é removido automaticamente
        cookie.setMaxAge(300);
        return cookie;
    }

    public Cookie getCookie(HttpServletRequest request, String name){
        return WebUtils.getCookie(request, name);
    }
}
