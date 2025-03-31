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
}
