package net.crud.crud.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import net.crud.crud.entities.DTOs.EditPasswordDTO;
import net.crud.crud.entities.DTOs.EditStatusDTO;
import net.crud.crud.entities.DTOs.UserEditDTO;
import net.crud.crud.entities.DTOs.UsuarioCadastroDTO;
import net.crud.crud.repositories.UserRepository;
import net.crud.crud.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    public User create(String userS, List<MultipartFile> arquivos) throws IOException {
        UsuarioCadastroDTO usuarioCadastroDTO =
                objectMapper.readValue(userS, UsuarioCadastroDTO.class);
        usuarioCadastroDTO.setArquivos(arquivos);
        User user = new User();
        BeanUtils.copyProperties(usuarioCadastroDTO, user);
        return userRepository.save(user);
    }

    public User getOne(Long id){
        return userRepository.findById(id).get();
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User update(UserEditDTO userEditDTO){
        User user = new User();
        BeanUtils.copyProperties(userEditDTO, user);

        return userRepository.save(user);
    }

//    public void editPassword(EditPasswordDTO editPasswordDTO){
//        User user = getOne(editPasswordDTO.getId());
//        user.setPassword(editPasswordDTO.getPassword());
//        userRepository.save(user);
//    }

//    public void editStatus(EditStatusDTO editStatusDTO){
//        User user = getOne(editStatusDTO.getId());
//        user.setStatus(editStatusDTO.getStatus());
//        userRepository.save(user);
//    }

    public void remove(Long id){
        userRepository.deleteById(id);
    }
}
