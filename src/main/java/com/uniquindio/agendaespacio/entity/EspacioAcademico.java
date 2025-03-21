package com.uniquindio.agendaespacio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class EspacioAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEspacioAcademico;

    @Column(nullable = false)
    @NotEmpty(message = "El nombre del espacio académico no puede ser nulo")
    private String nombre;

    @Column(nullable = false)
    private Integer semestre;

    @Column(nullable = false)
    @NotEmpty(message = "La descripcion no puede ser nula")
    private String descripcion;


    //<------------------- Auditoria--------------------------->
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
