package net.crud.crud.entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEditDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Integer age;
    private String status;
}
