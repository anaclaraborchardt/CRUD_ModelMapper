package net.crud.crud.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EditPasswordDTO {

    private Long id;
    private String password;
}
