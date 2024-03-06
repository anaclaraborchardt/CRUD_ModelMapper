package net.crud.crud.security;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import net.crud.crud.entities.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Builder
public class UsuarioDetailsEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Length(min = 6)
    private String password;

    @Column(unique = true, nullable = false, updatable = false)
    private String username;

    private boolean enabled;

    private Collection<Authorities> authorities;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    @OneToOne(mappedBy = "usuarioDetailsEntity")
    private User user;

}

