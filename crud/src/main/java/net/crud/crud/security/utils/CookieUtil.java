package net.crud.crud.security.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.util.WebUtils;

//cookies settings
public class CookieUtil {

    public Cookie cookieGeneratorJwt(UserDetails userDetails){
        String token = new JwtUtil().generateToken(userDetails);
        System.out.println(token);
        Cookie cookie = new Cookie("JWT", token);
        cookie.setPath("/");
        //depois do tempo passado, o cookie é removido automaticamente
        cookie.setMaxAge(300);
        return cookie;
    }

    public Cookie getCookie(HttpServletRequest request, String name) throws Exception {
        Cookie cookie =  WebUtils.getCookie(request, name);

        if(cookie != null){
            return cookie;
        }
        throw new Exception("Cookie não encontrado");
    }
}
