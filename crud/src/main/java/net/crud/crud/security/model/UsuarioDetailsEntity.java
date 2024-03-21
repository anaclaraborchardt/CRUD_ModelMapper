package net.crud.crud.security.model;

import jakarta.persistence.*;
import lombok.*;
import net.crud.crud.models.entities.User;
import net.crud.crud.security.model.Authorities;
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

