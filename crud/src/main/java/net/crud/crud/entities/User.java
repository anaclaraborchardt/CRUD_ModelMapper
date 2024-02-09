package net.crud.crud.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Integer age;
    private String status;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Arquivo> arquivos;

    public void setArquivos(List<MultipartFile> arquivos) throws IOException {
        for(MultipartFile arquivoFor : arquivos){
            Arquivo arquivo = new Arquivo();
            arquivo.setName(arquivoFor.getOriginalFilename());
            arquivo.setTipo(arquivoFor.getContentType());
            arquivo.setDados(arquivoFor.getBytes());
            this.arquivos.add(arquivo);
        }

    }
}
