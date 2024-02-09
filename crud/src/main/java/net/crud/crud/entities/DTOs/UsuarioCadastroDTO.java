package net.crud.crud.entities.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.crud.crud.entities.Arquivo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class UsuarioCadastroDTO {

    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private Integer age;
    @NonNull
    private String status;
    private List<Arquivo> arquivos = new ArrayList<>();

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
