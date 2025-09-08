package com.uniquindio.agendaespacio.service.imp;

import com.uniquindio.agendaespacio.entity.Facultad;
import com.uniquindio.agendaespacio.repository.FacultadRepository;
import com.uniquindio.agendaespacio.service.IFacultadService;
import com.uniquindio.agendaespacio.util.constants.Constants;
import com.uniquindio.agendaespacio.util.utilities.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FacultadService implements IFacultadService {

    @Autowired
    private FacultadRepository facultadRepository;

    private final String classLog = getClass().getName() + '.';


    /**
     * Metodo que permite crear la facultad
     * @param facultad que se desea crear
     * @return la facultad
     */
    @Override
    public Facultad crearFacultad(Facultad facultad) {
        log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"crearFacultad");
        if(Validation.isNullOrEmpty(facultadRepository.findByNombreFacultadIgnoreCase(facultad.getNombreFacultad()))){
            facultad.setNombreFacultad(facultad.getNombreFacultad());
            facultad.setFechaCreacion(new Date());
            facultad.setIdUsuarioCreacion(facultad.getIdUsuarioCreacion());
            Facultad newFacultad = facultadRepository.save(facultad);
            if(!Validation.isNullOrEmpty(newFacultad)){
                return newFacultad;
            }
            log.error(Constants.MSN_FIN_LOG_INFO + classLog + "No se pudo crear la facultad");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se pudo crear la facultad");
        }
        log.info(Constants.MSN_FIN_LOG_INFO+classLog+"crearFacultad");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La facultad ya existe");
    }

    /**
     * Metodo que permite actualizar una facultad
     * @param facultad a actualizar
     * @return facultad actualizada
     */
    @Override
    public Facultad actualizarFacultad(Facultad facultad) {
        log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"actualizarFacultad");
        Optional<Facultad> lista = facultadRepository.findById(facultad.getIdFacultad());
        if(lista.isPresent()){
            if(Validation.isNullOrEmpty(facultadRepository.findOneByNombreFacultadAndIdFacultadNot(facultad.getNombreFacultad(),facultad.getIdFacultad()))){
                facultad.setNombreFacultad(facultad.getNombreFacultad());
                facultad.setFechaModificacion(new Date());
                facultad.setIdUsuarioModificacion(facultad.getIdUsuarioModificacion());
                facultadRepository.save(facultad);
                return facultad;
            }
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "No se pudo actualizar la facultad");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La facutad ya existe");
        }
        log.info(Constants.MSN_FIN_LOG_INFO+classLog+"actualizarFacultad");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El registro no existe");
    }

    @Override
    public List<Facultad> listarFacultads() {
        List<Facultad> listaFacultad = facultadRepository.findAll();
        if(!Validation.isNullOrEmpty(listaFacultad)){
            return listaFacultad;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Lista de facultades esta vacia");
    }

    @Override
    public void eliminarFacultad(Integer idFacultad) {
        log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"eliminarFacultad");
        Optional<Facultad> lista = facultadRepository.findById(idFacultad);
        if(lista.isEmpty()){
            log.info(Constants.MSN_FIN_LOG_INFO+classLog + "eliminarFacultad");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La facultad no existe");
        }
        facultadRepository.deleteById(idFacultad);
        log.info(Constants.MSN_FIN_LOG_INFO+classLog+"eliminarFacultad");

    }

    @Override
    public List<Facultad> crearFacultadesMasivo(List<Facultad> facultades) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "crearFacultadesMasivo");
        
        List<Facultad> facultadesCreadas = new java.util.ArrayList<>();
        
        for (Facultad facultad : facultades) {
            try {
                // Verificar si la facultad ya existe
                if (Validation.isNullOrEmpty(facultadRepository.findByNombreFacultadIgnoreCase(facultad.getNombreFacultad()))) {
                    facultad.setNombreFacultad(facultad.getNombreFacultad());
                    facultad.setFechaCreacion(new Date());
                    facultad.setIdUsuarioCreacion(facultad.getIdUsuarioCreacion());
                    Facultad newFacultad = facultadRepository.save(facultad);
                    
                    if (!Validation.isNullOrEmpty(newFacultad)) {
                        facultadesCreadas.add(newFacultad);
                    }
                } else {
                    log.warn("La facultad con nombre '{}' ya existe, se omite", facultad.getNombreFacultad());
                }
            } catch (Exception e) {
                log.error("Error al crear la facultad '{}': {}", facultad.getNombreFacultad(), e.getMessage());
            }
        }
        
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "crearFacultadesMasivo - Se crearon {} de {} facultades", 
                facultadesCreadas.size(), facultades.size());
        
        return facultadesCreadas;
    }
}
