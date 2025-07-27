package com.uniquindio.agendaespacio.service.imp;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uniquindio.agendaespacio.entity.EspacioPrograma;
import com.uniquindio.agendaespacio.entity.Grupo;
import com.uniquindio.agendaespacio.entity.GrupoRelacion;
import com.uniquindio.agendaespacio.repository.GrupoRelacionRepository;
import com.uniquindio.agendaespacio.service.IGrupoRelacionService;
import com.uniquindio.agendaespacio.util.constants.Constants;
import com.uniquindio.agendaespacio.util.utilities.Validation;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GrupoRelacionService implements IGrupoRelacionService {

    @Autowired
    private GrupoRelacionRepository grupoRelacionRepository;

    private final String classLog = getClass().getName() + '.';

    /*
     * @Override
     * public GrupoRelacion crearGrupoRelacion(GrupoRelacion grupoRelacion) {
     * 
     * log.info(Constants.MSN_INICIO_LOG_INFO, classLog + "crearGrupoRelacion");
     * Optional<GrupoRelacion> grupoRelacionExiste = grupoRelacionRepository
     * .findByGrupoAndSedeAndEspacioAcademicoAndFacultad(grupoRelacion.getGrupo(),
     * grupoRelacion.getSede(),
     * grupoRelacion.getEspacioAcademico(), grupoRelacion.getFacultad());
     * if (grupoRelacionExiste.isPresent()) {
     * throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
     * "El grupo relacion ya existe");
     * }
     * GrupoRelacion newGrupoRelacion = new GrupoRelacion();
     * 
     * newGrupoRelacion.setGrupo(grupoRelacion.getGrupo());
     * newGrupoRelacion.setSede(grupoRelacion.getSede());
     * newGrupoRelacion.setEspacioAcademico(grupoRelacion.getEspacioAcademico());
     * newGrupoRelacion.setFacultad(grupoRelacion.getFacultad());
     * 
     * return grupoRelacionRepository.save(grupoRelacion);
     * 
     * }
     * 
     */
    @Override
    public GrupoRelacion crearGrupoRelacion(GrupoRelacion grupoRelacion) {

        log.info(Constants.MSN_INICIO_LOG_INFO, classLog + "crearGrupoRelacion");

        // Extraemos un EspacioPrograma (el primero)
        EspacioPrograma espacioPrograma = grupoRelacion.getEspacioPrograma();
        if (espacioPrograma == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Espacio programa no puede ser nulo");
        }

        // Verificamos si ya existe la relación
        Optional<GrupoRelacion> grupoRelacionExiste = grupoRelacionRepository
                .findBySedeAndFacultadAndEspacioPrograma(grupoRelacion.getSede(), grupoRelacion.getFacultad(),
                        espacioPrograma);

        if (grupoRelacionExiste.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El grupo relacion ya existe");
        }

        
        GrupoRelacion nuevoGrupoRelacion = new GrupoRelacion();
        nuevoGrupoRelacion.setSede(grupoRelacion.getSede());
        nuevoGrupoRelacion.setFacultad(grupoRelacion.getFacultad());
        nuevoGrupoRelacion.setEspacioPrograma(grupoRelacion.getEspacioPrograma());
        nuevoGrupoRelacion.setIdUsuarioCreacion(grupoRelacion.getIdUsuarioCreacion());

        return grupoRelacionRepository.save(nuevoGrupoRelacion);
    }

    @Override
    public List<GrupoRelacion> listarGrupoRelacion() {

        List<GrupoRelacion> listaGr = grupoRelacionRepository.findAll();
        if (!Validation.isNullOrEmpty(listaGr)) {
            return listaGr;

        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "lista grupo relacion esta vacia");
    }

    @Override
    public void eliminarGrupoRelacion(Integer idGrupoRelacion) {

        log.info(Constants.MSN_INICIO_LOG_INFO, classLog + "eliminarGrupoRelacion");
        Optional<GrupoRelacion> lista = grupoRelacionRepository.findById(idGrupoRelacion);
        if (lista.isEmpty()) {
            log.info(Constants.MSN_FIN_LOG_INFO, classLog + "eliminarGrupoRelacion");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el grupo relacion no existe");
        }
        grupoRelacionRepository.deleteById(idGrupoRelacion);
    }

    /*
     * @Override
     * public void eliminarGrupoRelacionporGrupo(Grupo grupo) {
     * 
     * log.info(Constants.MSN_INICIO_LOG_INFO + classLog +
     * "eliminarGrupoRelacionporGrupo");
     * Optional<GrupoRelacion> lista = grupoRelacionRepository.findByGrupo(grupo);
     * if (lista.isEmpty()) {
     * log.info(Constants.MSN_FIN_LOG_INFO + classLog +
     * "eliminarGrupoRelacionporGrupo");
     * throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
     * "el grupo relacion no existe");
     * }
     * grupoRelacionRepository.delete(lista.get());
     * log.info(Constants.MSN_INICIO_LOG_INFO + classLog +
     * "eliminarGrupoRelacionporGrupo");
     * }
     */

}
