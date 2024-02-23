package net.crud.crud.security;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public enum Authorities implements GrantedAuthority {


    GET("Get"),
    POST("Post"),
    PUT("Put"),
    DELETE("Delete");

    private final String nome;

    public static Authorities getAuthorities(String name){
        return valueOf(name.toUpperCase());
    }
    @Override
    public String getAuthority() {
        return nome;
    }
}
