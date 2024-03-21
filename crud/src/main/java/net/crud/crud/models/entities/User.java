package net.crud.crud.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.crud.crud.security.model.UsuarioDetailsEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private String name;
    private String email;
//    private String password;
    private Integer age;
//    private String status;
//    private boolean enabled;
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Arquivo> arquivos;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private UsuarioDetailsEntity usuarioDetailsEntity;

}
