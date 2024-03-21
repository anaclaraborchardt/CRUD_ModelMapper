package net.crud.crud.security.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public enum Authorities implements GrantedAuthority {


    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private final String nome;

    public static Authorities getAuthorities(String name){
        return valueOf(name.toUpperCase());
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
