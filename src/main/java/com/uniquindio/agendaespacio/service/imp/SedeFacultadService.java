package com.uniquindio.agendaespacio.service.imp;

import com.uniquindio.agendaespacio.entity.Facultad;
import com.uniquindio.agendaespacio.entity.SedeFacultad;
import com.uniquindio.agendaespacio.repository.SedeFacultadRepository;
import com.uniquindio.agendaespacio.service.ISedeFacultadService;
import com.uniquindio.agendaespacio.util.constants.Constants;
import com.uniquindio.agendaespacio.util.utilities.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@Slf4j
public class SedeFacultadService implements ISedeFacultadService {

    @Autowired
    private SedeFacultadRepository sedeFacultadRepository;

    private final String classLog = getClass().getName() + '.';


    @Override
    public List<SedeFacultad> crearSedeFacultad(List<SedeFacultad> listaSf) {
        for (SedeFacultad sedeFacultad : listaSf) {
            if (sedeFacultad.getSede() == null || sedeFacultad.getFacultad() == null) {
                throw new RuntimeException("Sede o Facultad no pueden ser nulos");
            }
        }
      return   sedeFacultadRepository.saveAll(listaSf);
    }

    @Override
    public List<SedeFacultad> listarSedeFacultad() {
      List<SedeFacultad> sflist = sedeFacultadRepository.findAll();
      if(!Validation.isNullOrEmpty(sflist)){
          return sflist;
      }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "lista sede facultad esta vacia");
    }

    @Override
    public void elminarSedeFacultad(Integer idSedeFacultad) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "elminarSedeFacultad");
        Optional<SedeFacultad> lista= sedeFacultadRepository.findById(idSedeFacultad);
        if(lista.isEmpty()){
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "elminarSedeFacultad");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la sede facultad no exite");
        }
        sedeFacultadRepository.deleteById(idSedeFacultad);
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "elminarSedeFacultad");

    }

    @Override
    public void elminarSedeFacultadPorFacultad(Facultad facultad) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "elminarSedeFacultadPorFacultad");
        Optional<SedeFacultad> lista= sedeFacultadRepository.findByFacultad(facultad);
        if(lista.isEmpty()){
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarFacultadSolicitante");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la facultad solicitante no exite");
        }
        sedeFacultadRepository.delete(lista.get());
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "elminarSedeFacultadPorFacultad");
    }

    @Override
    public List<SedeFacultad> crearSedeFacultadesMasivo(List<SedeFacultad> sedeFacultades) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "crearSedeFacultadesMasivo");
        
        List<SedeFacultad> sedeFacultadesCreadas = new java.util.ArrayList<>();
        
        for (SedeFacultad sedeFacultad : sedeFacultades) {
            try {
                // Verificar si la relación ya existe
                if (sedeFacultad.getSede() != null && sedeFacultad.getFacultad() != null) {
                    // Verificar si ya existe esta combinación sede-facultad
                    Optional<SedeFacultad> existente = sedeFacultadRepository.findBySedeAndFacultad(
                        sedeFacultad.getSede(), sedeFacultad.getFacultad());
                    
                    if (existente.isEmpty()) {
                        sedeFacultad.setFechaCreacion(new Date());
                        sedeFacultad.setIdUsuarioCreacion(sedeFacultad.getIdUsuarioCreacion());
                        SedeFacultad newSedeFacultad = sedeFacultadRepository.save(sedeFacultad);
                        
                        if (!Validation.isNullOrEmpty(newSedeFacultad)) {
                            sedeFacultadesCreadas.add(newSedeFacultad);
                        }
                    } else {
                        log.warn("La relación sede '{}' - facultad '{}' ya existe, se omite", 
                                sedeFacultad.getSede().getNombreSede(), 
                                sedeFacultad.getFacultad().getNombreFacultad());
                    }
                } else {
                    log.warn("Sede o facultad nula en el registro, se omite");
                }
            } catch (Exception e) {
                log.error("Error al crear la relación sede-facultad: {}", e.getMessage());
            }
        }
        
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "crearSedeFacultadesMasivo - Se crearon {} de {} relaciones", 
                sedeFacultadesCreadas.size(), sedeFacultades.size());
        
        return sedeFacultadesCreadas;
    }
}
