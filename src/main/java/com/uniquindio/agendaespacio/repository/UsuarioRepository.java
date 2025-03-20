package com.uniquindio.agendaespacio.repository;

import com.uniquindio.agendaespacio.entity.Usuario;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


    Usuario findOneByUsuarioAndIdUsuarioNot(String usuario, Integer idUsuario);

    Optional<Usuario> findByUsuarioIgnoreCase(String usuario);

    Usuario findByUsuario(String usuario);
}