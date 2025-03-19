package com.uniquindio.agendaespacio.repository;

import com.uniquindio.agendaespacio.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    List<Rol> findByNombreIgnoreCase(String nombre);
    Rol findOneByNombreAndIdRolNot(String nombre, Integer idRol);
}
