package com.uniquindio.agendaespacio.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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

    public Sede crearSede(Sede sede) {

        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "crearSede");
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

}
