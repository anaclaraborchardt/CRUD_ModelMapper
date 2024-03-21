package net.crud.crud.repositories;


import net.crud.crud.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsuarioDetailsEntity_Username(String username);

    Page<User> findAll(Pageable pageable);

    Page<User> findUserByEmailContaining(String email, Pageable pageable);



}