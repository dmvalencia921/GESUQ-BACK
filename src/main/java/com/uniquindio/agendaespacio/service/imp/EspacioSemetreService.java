package com.uniquindio.agendaespacio.service.imp;

import com.uniquindio.agendaespacio.entity.EspacioAcademico;
import com.uniquindio.agendaespacio.entity.EspacioSemestre;
import com.uniquindio.agendaespacio.entity.SedeFacultad;
import com.uniquindio.agendaespacio.entity.Semestre;
import com.uniquindio.agendaespacio.repository.EspacioSemestreRepository;
import com.uniquindio.agendaespacio.service.IEspacioSemestreService;
import com.uniquindio.agendaespacio.util.constants.Constants;
import com.uniquindio.agendaespacio.util.utilities.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EspacioSemetreService implements IEspacioSemestreService {

    @Autowired
    private EspacioSemestreRepository espacioSemestreRepository;

    private final String classLog = getClass().getName() + '.';

    @Override
    public List<EspacioSemestre> crearEspacioSemestre(List<EspacioSemestre> listaEs) {
        for (EspacioSemestre espacioSemestre : listaEs) {
            if (espacioSemestre.getEspacioAcademico()== null || espacioSemestre.getSemestre() == null) {
                throw new RuntimeException("espacio academico o semestre no pueden ser nulos");
            }
        }
        return   espacioSemestreRepository.saveAll(listaEs);
    }

    @Override
    public List<EspacioSemestre> listarEspacioSemestre() {
        List<EspacioSemestre> eslist = espacioSemestreRepository.findAll();
        if(!Validation.isNullOrEmpty(eslist)){
            return  eslist;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "lista espacio semestre esta vacia");
    }

    @Override
    public void eliminarEspacioSemestre(Integer idEspacioSemestre) {
        log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"eliminarEspacioSemestre");
        Optional<EspacioSemestre> lista = espacioSemestreRepository.findById(idEspacioSemestre);
        if(lista.isEmpty()){
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarEspacioSemestre");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la espacio semestre no existe");
        }
        espacioSemestreRepository.deleteById(idEspacioSemestre);
        log.info(Constants.MSN_FIN_LOG_INFO+classLog+"eliminarEspacioSemestre");
    }

    @Override
    public void eiminarEspacioSemestrePorSemestre(Semestre semestre) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "eiminarEspacioSemestrePorSemestre");
        Optional<EspacioSemestre> lista= espacioSemestreRepository.findBySemestre(semestre);
        if(lista.isEmpty()){
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eiminarEspacioSemestrePorSemestre");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El semestre espacio no exite");
        }
        espacioSemestreRepository.delete(lista.get());
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "elminarSedeFacultadPorFacultad");
    }
}
