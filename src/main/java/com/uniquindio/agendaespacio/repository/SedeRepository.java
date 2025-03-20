package com.uniquindio.agendaespacio.repository;

import com.uniquindio.agendaespacio.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SedeRepository extends JpaRepository<Sede,Integer>  {
    List<Sede>  findByNombreSedeIgnoreCase(String nombre);
    Sede findOneByNombreSedeAndIdSedeNot(String nombre, Integer idSede);
}
