package com.uniquindio.agendaespacio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @NotEmpty(message = "La descripcion no puede ser nula")
    private String descripcion;

    @OneToMany(mappedBy = "espacioAcademico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ReservaEspacio> listReservas = new HashSet<>();


    @OneToMany(mappedBy = "espacioAcademico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    Set<EspacioSemestre> listaEspacioSemestre = new HashSet<>();

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
