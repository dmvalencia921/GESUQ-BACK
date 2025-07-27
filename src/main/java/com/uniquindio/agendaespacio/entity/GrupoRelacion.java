package com.uniquindio.agendaespacio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
public class GrupoRelacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGrupoRelacion;

    @ManyToOne
    @JoinColumn(name = "id_facultad", nullable = false)
    private Facultad facultad;

    @ManyToOne
    @JoinColumn(name = "id_sede", nullable = false)
    private Sede sede;

    @ManyToOne
    @JoinColumn(name = "id_espacio", nullable = false)
    private EspacioPrograma espacioPrograma;
   



    /*
     * @ManyToOne
     * 
     * @JoinColumn(name="id_espacio_academico",nullable = false)
     * private EspacioAcademico espacioAcademico;
     */

    // <------------------- Auditoria--------------------------->

    /**
     * Id del usuario que creo el registro.
     */
    @Column(name = "id_usu_creacion", nullable = false, length = 15)
    private String idUsuarioCreacion;

    /**
     * Id del usuario que modifico el registro.
     */
    @Column(name = "id_usu_actualizacion", nullable = true, length = 15)
    private String idUsuarioModificacion;

    /**
     * Fecha de creación del registro
     */
    @Column(name = "fecha_creacion", nullable = false, length = 30)
    private Date fechaCreacion = new Date();

    /**
     * Fecha de modificación del registro
     */
    @Column(name = "fecha_actualizacion", nullable = true, length = 30)
    private Date fechaModificacion;

}
