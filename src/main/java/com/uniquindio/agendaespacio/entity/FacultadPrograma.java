package com.uniquindio.agendaespacio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class FacultadPrograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFacultadPrograma;

    @ManyToOne
    @JoinColumn(name = "id_facultad", nullable = false)
    private Facultad facultad;

    @ManyToOne
    @JoinColumn(name = "cod_programa",nullable = false)
    private Programa programa;
    //-----------------> Auditoria <--------------------

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
