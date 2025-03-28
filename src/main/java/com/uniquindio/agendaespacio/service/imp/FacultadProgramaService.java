package com.uniquindio.agendaespacio.service.imp;

import com.uniquindio.agendaespacio.entity.Facultad;
import com.uniquindio.agendaespacio.entity.FacultadPrograma;
import com.uniquindio.agendaespacio.entity.Programa;
import com.uniquindio.agendaespacio.repository.FacultadProgramaRespository;
import com.uniquindio.agendaespacio.service.IFacultadProgramaService;
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
public class FacultadProgramaService implements IFacultadProgramaService {

    @Autowired
    private FacultadProgramaRespository facultadProgramaRespository;

    private final String classLog = getClass().getName() + '.';

    @Override
    public List<FacultadPrograma> crearFacultadPrograma(List<FacultadPrograma> listaFp) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "crearFacultadPrograma");

        if (listaFp.isEmpty()) {
            log.warn("La lista de programas está vacía, no se realizará ninguna operación.");
            return new ArrayList<>();
        }

        /**
         * Asumimos que todos van a tener la misma facultad
         */

        Facultad facultad = listaFp.get(0).getFacultad();

        // Obtener los programas existentes en la BD para esa facultad
        List<FacultadPrograma> listaBD = facultadProgramaRespository.findByFacultad(facultad);

        Set<String> codigosExistentes = listaBD.stream()
                .map(fp -> fp.getPrograma().getCodPrograma())
                .collect(Collectors.toSet());

        // Filtrar programas nuevos (que no existen en la BD)
        List<FacultadPrograma> nuevosRegistros = listaFp.stream()
                .filter(fp -> !codigosExistentes.contains(fp.getPrograma().getCodPrograma()))
                .peek(fp -> {
                    fp.setFacultad(facultad); // Asegurar que todos tengan la misma facultad
                    fp.setFechaCreacion(new Date());
                })
                .collect(Collectors.toList());

        // Guardar nuevos registros en la base de datos
        List<FacultadPrograma> facultadesGuardadas = facultadProgramaRespository.saveAll(nuevosRegistros);
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "crearFacultadPrograma - Registros guardados: " + facultadesGuardadas.size());
        return facultadesGuardadas;
    }

    @Override
    public List<FacultadPrograma> listarFacultadPrograma() {
        List<FacultadPrograma> fsList = facultadProgramaRespository.findAll();
        if (!Validation.isNullOrEmpty(fsList)) {
            return fsList;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "lista facultad programa esta vacia");
    }

    @Override
    public List<FacultadPrograma> listarFacultadProgramaPorFacultad(Facultad facultad) {
        List<FacultadPrograma> fpList = facultadProgramaRespository.findByFacultad(facultad);
        if (!Validation.isNullOrEmpty(fpList)) {
            return fpList;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "lista facultad programa  esta vacia porque no hay con ese codigo de facultad");
    }

    @Override
    public void eliminarFacultadPrograma(Programa programa) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "eliminar facultadSolicitante");
        Optional<FacultadPrograma> lista = facultadProgramaRespository.findByPrograma(programa);
        if (lista.isEmpty()) {
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarFacultadSolicitante");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La facultad programa no exite");
        }
        facultadProgramaRespository.delete(lista.get());
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarFacultadSolicitante");
    }

    @Override
    public void eliminarFaculPrograma(Integer idFacultadPrograma) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "eliminarFaculPrograma");
        Optional<FacultadPrograma> lista = facultadProgramaRespository.findById(idFacultadPrograma);
        if (lista.isEmpty()) {
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarFacultadSolicitante");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La facultad programa no exite");
        }
        facultadProgramaRespository.deleteById(idFacultadPrograma);
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarFaculPrograma");
    }
}
