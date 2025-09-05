package com.uniquindio.agendaespacio.repository;

import com.uniquindio.agendaespacio.entity.EspacioAcademico;
import com.uniquindio.agendaespacio.entity.ReservaEspacio;
import com.uniquindio.agendaespacio.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservaEspacioRepository extends JpaRepository<ReservaEspacio, Integer> {

    /*
     * @Query("SELECT COUNT(r) > 0 FROM ReservaEspacio r WHERE r.espacioAcademico.idEspacioAcademico = :idEspacio AND r.fechaReservaEspacio = :fecha AND r.horario = :horario"
     * )
     * boolean existsByEspacioAndFechaAndHorario(@Param("idEspacio") Integer
     * idEspacio, @Param("fecha") Date fecha, @Param("horario") String horario);
     */
    @Query("SELECT COUNT(r) > 0 FROM ReservaEspacio r " +
            "WHERE r.grupoRelacion.idGrupoRelacion = :idGrupoRelacion " +
            "AND r.fechaReservaEspacio = :fecha " +
            "AND r.horario = :horario")
    boolean existsReserva(
            @Param("idGrupoRelacion") Integer idGrupoRelacion,
            @Param("fecha") Date fecha,
            @Param("horario") String horario);

    List<ReservaEspacio> findByUsuario(Usuario usuario);

    ReservaEspacio findOneByIdReservaEspacio(Integer id);

}
