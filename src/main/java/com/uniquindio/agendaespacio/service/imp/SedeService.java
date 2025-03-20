package com.uniquindio.agendaespacio.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.uniquindio.agendaespacio.entity.Sede;
import com.uniquindio.agendaespacio.repository.SedeRepository;
import com.uniquindio.agendaespacio.service.ISedeService;
import com.uniquindio.agendaespacio.util.constants.Constants;
import com.uniquindio.agendaespacio.util.utilities.Validation;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SedeService implements ISedeService {

    @Autowired
    private SedeRepository sedeRepository;

    private final String classLog = getClass().getName() + '.';

    /**
     * Metodo que permite crear la sede
     * 
     * @param sede que se desea crear
     * @return la sede
     */

    public Sede crearSede(Sede sede) {

        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "crearSede");
        if (Validation.isNullOrEmpty(sedeRepository.findByNombreSedeIgnoreCase(sede.getNombreSede())))
            sede.setNombreSede(sede.getNombreSede());
            sede.setUbicacion(sede.getUbicacion());
            Sede newSede = sedeRepository.save(sede);
            
        if (!Validation.isNullOrEmpty(newSede)) {
            return newSede;
        }
        log.error(Constants.MSN_FIN_LOG_INFO + classLog + "No se pudo crear la sede");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La sede ya existe");

    }

    @Override
    public Sede actualizarSede(Sede sede) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "actualizarSede");
        Optional<Sede> lista = sedeRepository.findById(sede.getIdSede());
        if (lista.isPresent()) {
            if (Validation.isNullOrEmpty(sedeRepository.findOneByNombreSedeAndIdSedeNot(sede.getNombreSede(), sede.getIdSede()))) {
                sede.setNombreSede(sede.getNombreSede());
                sede.setUbicacion(sede.getUbicacion());
                sedeRepository.save(sede);
                return sede;
            }
            log.error(Constants.MSN_FIN_LOG_INFO + classLog + "No se pudo actualizar la sede");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La sede ya existe");
        }
        log.info(Constants.MSN_FIN_LOG_INFO+"actualizarSede");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La sede no existe");
    }

    @Override
    public List<Sede> listarSedes(){

        List<Sede> listSede=sedeRepository.findAll();
        if (!Validation.isNullOrEmpty(listSede)) {
            return listSede;            
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Lista de sedes vacia");
    }

    @Override
    public void eliminarSede(Integer idSede){        
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "eliminarSede");
        Optional<Sede>lista =sedeRepository.findById(idSede);
        if (lista.isEmpty()) {
            log.error(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarSede");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La sede no existe");          
        }
        sedeRepository.deleteById(idSede);
        log.info(Constants.MSN_FIN_LOG_INFO+classLog+"eliminarFacultad");
    }

}
