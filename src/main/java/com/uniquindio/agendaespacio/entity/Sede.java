package com.uniquindio.agendaespacio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSede;

    @Column(nullable = false)
    @NotEmpty(message = "El nombre de la sede no puede ser nulo")
    private  String nombreSede;

    @Column(nullable = false)
    @NotEmpty(message = "La ubicacion de la sede no puede ser nula")
    private String ubicacion;

/*      @OneToMany(mappedBy = "sede", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Grupo> listaGrupos = new HashSet<>(); */


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
