package net.crud.crud.controllers;

import lombok.AllArgsConstructor;
import net.crud.crud.entities.DTOs.EditPasswordDTO;
import net.crud.crud.entities.DTOs.EditStatusDTO;
import net.crud.crud.entities.DTOs.UserEditDTO;
import net.crud.crud.entities.User;
import net.crud.crud.repositories.UserRepository;
import net.crud.crud.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> post (@RequestParam String userS, @RequestParam List<MultipartFile> arquivos) throws IOException {
        try {
            return new ResponseEntity<>(userService.create(userS, arquivos), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne (@PathVariable Long id){
        try {
            return new ResponseEntity<>(userService.getOne(id), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll (){
        try {
            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

//    @PatchMapping("/password")
//    public ResponseEntity<?> editPassword (@RequestBody EditPasswordDTO editPasswordDTO){
//        try {
//            userService.editPassword(editPasswordDTO);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }catch(Exception e){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//        }
//    }

//    @PatchMapping("/status")
//    public ResponseEntity<?> editStatus (@RequestBody EditStatusDTO editStatusDTO){
//        try {
//            userService.editStatus(editStatusDTO);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }catch(Exception e){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//        }
//    }

    @PutMapping
    public ResponseEntity<?> update (@RequestBody UserEditDTO userEditDTO){
        try {
            userService.update(userEditDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove (@PathVariable Long id){
        try {
            userService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
