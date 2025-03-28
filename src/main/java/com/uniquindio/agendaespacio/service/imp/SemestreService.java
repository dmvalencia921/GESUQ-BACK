package com.uniquindio.agendaespacio.service.imp;

import com.uniquindio.agendaespacio.entity.Semestre;
import com.uniquindio.agendaespacio.repository.SemestreRepository;
import com.uniquindio.agendaespacio.service.ISemestreService;
import com.uniquindio.agendaespacio.util.constants.Constants;
import com.uniquindio.agendaespacio.util.utilities.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.yaml.snakeyaml.scanner.Constant;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SemestreService implements ISemestreService {

    @Autowired
    private SemestreRepository semestreRepository;

    private final String classLog = getClass().getName() + '.';


    /**
     * Metodo que permite crear el semestre
     * @param semestre a crear
     * @return semestre creado
     */
    @Override
    public Semestre crearSemestre(Semestre semestre) {
       log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"crearSemestre");
       if(Validation.isNullOrEmpty(semestreRepository.findByNoSemestre(semestre.getNoSemestre()))){
           semestre.setNoSemestre(semestre.getNoSemestre());
           semestre.setDescripcion(semestre.getDescripcion());
           semestre.setFechaCreacion(new Date());
           semestre.setIdUsuarioCreacion(semestre.getIdUsuarioCreacion());
           Semestre newSemestre = semestreRepository.save(semestre);
           if(!Validation.isNullOrEmpty(newSemestre)){
               return newSemestre;
           }
           log.error(Constants.MSN_INICIO_LOG_INFO+classLog+"No se puede crear el semestre");
           throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se puede crear el semestre");

       }
       log.info(Constants.MSN_FIN_LOG_INFO+classLog+"crearSemestre");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El semestre ya existe");
    }

    /**
     * Metodo que permite actualizar el el semestre
     * @param semestre a actualizar
     * @return semestre actualizado
     */
    @Override
    public Semestre actualizarSemestre(Semestre semestre) {
        log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"actualizarSemestre");
        Optional<Semestre> lista = semestreRepository.findById(semestre.getIdSemestre());
        if(lista.isPresent()){
            if(Validation.isNullOrEmpty(semestreRepository.findOneByNoSemestreAndIdSemestreNot(semestre.getNoSemestre(), semestre.getIdSemestre()))){
                semestre.setNoSemestre(semestre.getNoSemestre());
                semestre.setDescripcion(semestre.getDescripcion());
                semestre.setFechaModificacion(new Date());
                semestre.setIdUsuarioModificacion(semestre.getIdUsuarioModificacion());
                semestreRepository.save(semestre);
                return semestre;
            }
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "No se pudo actualizar el semestre");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El semestre ya existe");
        }
        log.info(Constants.MSN_FIN_LOG_INFO+classLog+"actualizarSemestre");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El semestre no existe");
    }

    /**
     * Metodo que permite listar los semestres
     * @return lsta de semestres creados
     */
    @Override
    public List<Semestre> listarSemestre() {
        List<Semestre> lstSemestre = semestreRepository.findAll();
        if(!Validation.isNullOrEmpty(lstSemestre)){
            return lstSemestre;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Lista de semestre vacia");
    }

    @Override
    public void eliminarSemestre(Integer idSemestre) {
        log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"eliminarSemestre");
        Optional<Semestre> lista = semestreRepository.findById(idSemestre);
        if(lista.isEmpty()){
            log.info(Constants.MSN_FIN_LOG_INFO+classLog+"eliminarSemestre");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El semestre no existe");
        }
        semestreRepository.deleteById(idSemestre);
        log.info(Constants.MSN_FIN_LOG_INFO+classLog+"eliminarSemestre");
    }
}
