package com.uniquindio.agendaespacio.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class Grupo {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idGrupo;

@Column(nullable = false)
@NotEmpty(message = "El nombre del grupo no puede ser nulo")
private String nombreGrupo;

@ManyToOne
@JoinColumn(name = "idSemestre", nullable = false)
private Semestre semestre;

/* @OneToMany(mappedBy = "grupo",cascade = CascadeType.ALL,fetch =FetchType.LAZY)
@JsonIgnore
Set<GrupoRelacion>listaGrupoRelacion=new HashSet<>(); */

/* @ManyToOne
@JoinColumn(name = "cod_sede",nullable = false)
private Sede sede; */




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
