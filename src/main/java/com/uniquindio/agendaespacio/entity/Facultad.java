package com.uniquindio.agendaespacio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Facultad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFacultad;

    @Column(nullable = false)
    @NotEmpty(message = "El nombre de la facultad no puede ser nulo")
    private  String nombreFacultad;

    @OneToMany(mappedBy = "facultad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<FacultadPrograma> listaFacultadProgramas = new HashSet<>();
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
