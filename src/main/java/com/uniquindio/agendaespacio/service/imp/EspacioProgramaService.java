package com.uniquindio.agendaespacio.service.imp;

import com.uniquindio.agendaespacio.entity.*;
import com.uniquindio.agendaespacio.repository.EspacioAcademicoRepository;
import com.uniquindio.agendaespacio.repository.EspacioProgramaRepository;
import com.uniquindio.agendaespacio.repository.ProgramaRepository;
import com.uniquindio.agendaespacio.service.IEspacioProgramaService;
import com.uniquindio.agendaespacio.util.constants.Constants;
import com.uniquindio.agendaespacio.util.utilities.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EspacioProgramaService implements IEspacioProgramaService {

    @Autowired
    private EspacioProgramaRepository espacioProgramaRepository;

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private EspacioAcademicoRepository espacioAcademicoRepository;

    private final String classLog = getClass().getName() + '.';

    /*
     * @Override
     * public List<EspacioPrograma> crearEspacioPrograma(List<EspacioPrograma>
     * listaEp) {
     * log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "crearFacultadPrograma");
     * 
     * if (listaEp.isEmpty()) {
     * log.
     * warn("La lista de programas está vacía, no se realizará ninguna operación.");
     * return new ArrayList<>();
     * }
     * 
     * 
     * // Asumimos que todos van a tener la misma facultad
     * 
     * 
     * Programa programa = listaEp.get(0).getPrograma();
     * 
     * // Obtener los programas existentes en la BD para ese programa
     * List<EspacioPrograma> listaBD =
     * espacioProgramaRepository.findByPrograma(programa);
     * 
     * Set<Integer> IdExistentes = listaBD.stream()
     * .map(fp -> fp.getEspacioAcademico().getIdEspacioAcademico())
     * .collect(Collectors.toSet());
     * 
     * // Filtrar espacios académicos nuevos (que no existen en la BD)
     * List<EspacioPrograma> nuevosRegistros = listaEp.stream()
     * .filter(fp ->
     * !IdExistentes.contains(fp.getEspacioAcademico().getIdEspacioAcademico()))
     * .peek(fp -> {
     * fp.setPrograma(programa); // Asegurar que todos tengan el mismo programa
     * fp.setFechaCreacion(new Date());
     * })
     * .collect(Collectors.toList());
     * 
     * // Guardar nuevos registros en la base de datos
     * List<EspacioPrograma> programasGuardados =
     * espacioProgramaRepository.saveAll(nuevosRegistros);
     * log.info(Constants.MSN_FIN_LOG_INFO + classLog +
     * "crearFacultadPrograma - Registros guardados: "
     * + programasGuardados.size());
     * return programasGuardados;
     * }
     */

    @Override
    public List<EspacioPrograma> crearEspacioPrograma(List<EspacioPrograma> listaEp) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "crearFacultadPrograma");

        if (listaEp.isEmpty()) {
            log.warn("La lista de programas está vacía, no se realizará ninguna operación.");
            return new ArrayList<>();
        }
        List<EspacioPrograma> nuevosRegistros = new ArrayList<>();

        for (EspacioPrograma fp : listaEp) {
            if (fp.getEspacioAcademico() == null || fp.getPrograma() == null) {
                log.warn("Registro con espacio o programa nulo, se omite: {}", fp);
                continue;
            }
            Integer idEspacio = fp.getEspacioAcademico().getIdEspacioAcademico();
            Integer idPrograma = fp.getPrograma().getIdPrograma();

            // Obtener espacio y programa persistidos
            EspacioAcademico espacio = espacioAcademicoRepository.findById(idEspacio)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Espacio no encontrado: " + idEspacio));

            Programa programa = programaRepository.findById(idPrograma)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Programa no encontrado: " + idPrograma));

            // Verificar si ya existe la combinación
            /*
             * List<EspacioPrograma> existentes =
             * espacioProgramaRepository.findByPrograma(programa);
             * if (!existentes.isEmpty()) {
             * continue; // ya existe, no lo volvemos a guardar
             * }
             * 
             * 
             */

            boolean existentes = espacioProgramaRepository.existsByEspacioAcademicoAndPrograma(espacio, programa);
            if (existentes) {
                log.info("Ya existe el espacio-programa con Espacio {} y Programa {}, se omite", idEspacio, idPrograma);
                continue;
            }
            // Asignar entidades persistidas
            fp.setEspacioAcademico(espacio);
            fp.setPrograma(programa);
            fp.setFechaCreacion(new Date());

            nuevosRegistros.add(fp);
        }

        List<EspacioPrograma> guardados = espacioProgramaRepository.saveAll(
                nuevosRegistros);
        log.info("EspacioPrograma guardados: {}", guardados);
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "crearEspacioPrograma - Registros guardados: "
                + guardados.size());

        return guardados;
    }

    @Override
    public List<EspacioPrograma> listarEspacioPrograma() {
        List<EspacioPrograma> EsList = espacioProgramaRepository.findAll();
        if (!Validation.isNullOrEmpty(EsList)) {
            return EsList;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "lista facultad programa esta vacia");
    }

    @Override
    public List<EspacioPrograma> listarEspacioProgramaPorPrograma(Programa programa) {
        List<EspacioPrograma> epList = espacioProgramaRepository.findByPrograma(programa);
        if (!Validation.isNullOrEmpty(epList)) {
            return epList;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "lista facultad programa  esta vacia porque no hay con ese codigo de facultad");
    }

    @Override
    public void eliminarEspacioPrograma(EspacioAcademico espacioAcademico) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "eliminar facultadSolicitante");
        Optional<EspacioPrograma> lista = espacioProgramaRepository.findByEspacioAcademico(espacioAcademico);
        if (lista.isEmpty()) {
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarFacultadSolicitante");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la facultad solicitante no exite");
        }
        espacioProgramaRepository.delete(lista.get());
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarFacultadSolicitante");
    }

    @Override
    public void eliminarEspaPrograma(Integer idEspacioPrograma) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "eliminarFaculPrograma");
        Optional<EspacioPrograma> lista = espacioProgramaRepository.findById(idEspacioPrograma);
        if (lista.isEmpty()) {
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarFacultadSolicitante");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la facultad solicitante no exite");
        }
        espacioProgramaRepository.deleteById(idEspacioPrograma);
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarFaculPrograma");
    }
}
