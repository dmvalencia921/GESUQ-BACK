package com.uniquindio.agendaespacio.service.imp;

import com.uniquindio.agendaespacio.entity.EspacioAcademico;
import com.uniquindio.agendaespacio.repository.EspacioAcademicoRepository;
import com.uniquindio.agendaespacio.service.IEspacioAcademicoService;
import com.uniquindio.agendaespacio.util.constants.Constants;
import com.uniquindio.agendaespacio.util.utilities.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EspacioAcademicoService implements IEspacioAcademicoService {

    @Autowired
    private EspacioAcademicoRepository espacioAcademicoRepository;

    private final String classLog = getClass().getName() + '.';
    @Autowired
    private DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

    /**
     * Metodo que permite crear el espacio academico
     * @param espacioAcademico a crear
     * @return espacio academico creado
     */
    @Override
    public EspacioAcademico crearEspacioAcademico(EspacioAcademico espacioAcademico) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "crearEspacioAcademico");
        if (Validation.isNullOrEmpty(espacioAcademicoRepository.findByNombreIgnoreCaseAndSemestre(espacioAcademico.getNombre(), espacioAcademico.getSemestre()))) {
            espacioAcademico.setNombre(espacioAcademico.getNombre());
            espacioAcademico.setDescripcion(espacioAcademico.getDescripcion());
            espacioAcademico.setFechaCreacion(new Date());
            EspacioAcademico newEspacioAcademico = espacioAcademicoRepository.save(espacioAcademico);
            if (!Validation.isNullOrEmpty(newEspacioAcademico)) {
                return newEspacioAcademico;
            }
            log.error(Constants.MSN_FIN_LOG_INFO + classLog + "No se pudo crear el espacio academico");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo crear el espacio academico");
        }
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "crearEspacioAcademico");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Espacio academico ya existe");
    }

    /**
     * Metodo que permite actualizar un espacio academico
     * @param espacio academico a actialzar
     * @return esácop academico actualizado
     */
    @Override
    public EspacioAcademico actualizarEspacioAcademico(EspacioAcademico espacio) {
        log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"actualizarEspacioAcademico");
        Optional<EspacioAcademico> lista = espacioAcademicoRepository.findById(espacio.getIdEspacioAcademico());
        if(lista.isPresent()) {
            if(Validation.isNullOrEmpty(espacioAcademicoRepository.findOneByNombreAndIdEspacioAcademicoNot(espacio.getNombre(),espacio.getIdEspacioAcademico()))){
                espacio.setNombre(espacio.getNombre());
                espacio.setDescripcion(espacio.getDescripcion());
                espacio.setFechaModificacion(new Date());
                espacioAcademicoRepository.save(espacio);
                return espacio;
            }
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "No se pudo actualizar el espacio academico");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El espacio academico ya existe");
        }
        log.info(Constants.MSN_FIN_LOG_INFO+classLog+"actualizarEspacioAcademico");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El registro no existe");
    }

    /**
     * Metodo que permite listar los espacios academicos creados
     * @return lista de espacios academicos
     */
    @Override
    public List<EspacioAcademico> listarEspacioAcademicos() {
        List<EspacioAcademico> listEspacioAcademico = espacioAcademicoRepository.findAll();
        if(!Validation.isNullOrEmpty(listEspacioAcademico)) {
            return listEspacioAcademico;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La lista esta vacia");
    }

    @Override
    public void eliminarEspacioAcademico(Integer idEspacioAcademico) {
        log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"eliminarEspacioAcademico");
        Optional<EspacioAcademico> lista = espacioAcademicoRepository.findById(idEspacioAcademico);
        if(lista.isEmpty()){
            log.info(Constants.MSN_FIN_LOG_INFO+classLog+"EliminarEspacioAcademico");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Espacio academico no existe");
        }
        espacioAcademicoRepository.deleteById(idEspacioAcademico);
        log.info(Constants.MSN_FIN_LOG_INFO+classLog+"eliminarEspacioAcademico");

    }
}
