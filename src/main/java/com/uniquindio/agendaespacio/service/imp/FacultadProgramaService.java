package com.uniquindio.agendaespacio.service.imp;

import com.uniquindio.agendaespacio.entity.FacultadPrograma;
import com.uniquindio.agendaespacio.repository.FacultadProgramaRespository;
import com.uniquindio.agendaespacio.service.IFacultadProgramaService;
import com.uniquindio.agendaespacio.util.constants.Constants;
import com.uniquindio.agendaespacio.util.utilities.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.yaml.snakeyaml.scanner.Constant;

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
         * Asumimos que todos van a tener la misma facultad (similar a obneter e numero maximo donde asumimos que el
         * primer regstro es el número mayor)
         */
        Integer codFacultad = listaFp.get(0).getIdFacultad();

        List<FacultadPrograma> listaBD = facultadProgramaRespository.findByIdFacultad(codFacultad);

        Set<String> codigosNuevos = listaFp.stream()
                .map(FacultadPrograma::getCodPrograma)
                .collect(Collectors.toSet());

        List<FacultadPrograma> nuevosRegistros = listaFp.stream()
                .filter(fp -> listaBD.stream().noneMatch(existing -> existing.getCodPrograma().equals(fp.getCodPrograma())))
                .collect(Collectors.toList());

        List<FacultadPrograma> facultadesGuardadas = new ArrayList<>();
        for (FacultadPrograma fp : nuevosRegistros) {
            fp.setFechaCreacion(new Date());
            FacultadPrograma newFs = facultadProgramaRespository.save(fp);
            if (!Validation.isNullOrEmpty(newFs)) {
                facultadesGuardadas.add(newFs);
            }
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "crearFacultadPrograma");
        }

        /**
         * En este punto realizamos el filtro para mirar el codigo del programa que no existe para poder eliminarlo
         */
        List<FacultadPrograma> registrosParaEliminar = listaBD.stream()
                .filter(fp -> !codigosNuevos.contains(fp.getCodPrograma()))
                .collect(Collectors.toList());

        /**
         * si la lista no esta vacia entra y elimina los registros
         */
        if (!registrosParaEliminar.isEmpty()) {
            facultadProgramaRespository.deleteAll(registrosParaEliminar);
            log.info("Se eliminaron " + registrosParaEliminar.size() + " registros obsoletos de la facultad " + codFacultad);
        }

        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "crearFacultadesSolicitantes");
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
    public List<FacultadPrograma> listarFacultadProgramaPorFacultad(Integer Idfacultad) {
        List<FacultadPrograma> fpList = facultadProgramaRespository.findByIdFacultad(Idfacultad);
        if (!Validation.isNullOrEmpty(fpList)) {
            return fpList;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "lista facultad programa  esta vacia porque no hay con ese codigo de facultad");
    }

    @Override
    public void eliminarFacultadPrograma(String codPrograma) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "eliminar facultadSolicitante");
        Optional<FacultadPrograma> lista = facultadProgramaRespository.findByCodPrograma(codPrograma);
        if (lista.isEmpty()) {
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarFacultadSolicitante");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la facultad solicitante no exite");
        }
        facultadProgramaRespository.delete(lista.get());
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarFacultadSolicitante");
    }
}
