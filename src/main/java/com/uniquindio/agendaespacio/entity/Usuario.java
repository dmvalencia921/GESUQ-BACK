package com.uniquindio.agendaespacio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(nullable = false)
    @NotEmpty(message = "Los nombres del usuario no puede ser nulo")
    private String noDocumento;

    @Column(nullable = false)
    @NotEmpty(message = "Los nombres del usuario no puede ser nulo")
    private String nombres;

    @Column(nullable = false)
    @NotEmpty(message = "Los apellidos del usuario no pueden ser nulos")
    private String apellidos;

    @Column(nullable = false)
    @NotEmpty(message = "El usuario no puede ser nulo")
    private String usuario;

    @Column(nullable = false)
    @NotEmpty(message = "La clave del usuario no puede ser nula")
    private String clave;

    //comit de prueba
    @Column(columnDefinition = "boolean default true")
    private boolean activo;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private boolean admin;

    @Column(nullable = false)
    private String nombreRol;

    @Column(nullable = true)
    private String tokenRecuperacion;

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
