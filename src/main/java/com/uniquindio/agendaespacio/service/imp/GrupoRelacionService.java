package com.uniquindio.agendaespacio.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uniquindio.agendaespacio.entity.EspacioPrograma;
import com.uniquindio.agendaespacio.entity.FacultadPrograma;
import com.uniquindio.agendaespacio.entity.GrupoRelacion;
import com.uniquindio.agendaespacio.repository.EspacioProgramaRepository;
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

    @Autowired
    private EspacioProgramaRepository espacioProgramaRepository;

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

   /*  @Override
    public List<GrupoRelacion> crearGrupoRelacionMasivo(List<GrupoRelacion> gruposRelacion) {
       log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "crearFacultadProgramasMasivo");
        
        List<GrupoRelacion> grupoRelacionCreados = new java.util.ArrayList<>();
        
        for (GrupoRelacion grupoRelacion : gruposRelacion) {
            try {
                // Verificar si la relación ya existe
                if (grupoRelacion.getSede() != null && grupoRelacion.getFacultad() != null && grupoRelacion.getEspacioPrograma().getEspacioAcademico() != null
                && grupoRelacion.getEspacioPrograma().getPrograma() != null
                )
                 {
                    // Verificar si ya existe esta combinación facultad-programa
                    Optional<GrupoRelacion> existente = grupoRelacionRepository.findByFacultad(grupoRelacion.getFacultad());

                    if (existente.isEmpty()) {
                        grupoRelacion.setFechaCreacion(new Date());;
                        grupoRelacion.setIdUsuarioCreacion(grupoRelacion.getIdUsuarioCreacion());
                        GrupoRelacion newGrupoRelacion = grupoRelacionRepository.save(grupoRelacion);
                        
                        if (!Validation.isNullOrEmpty(newGrupoRelacion)) {
                            grupoRelacionCreados.add(newGrupoRelacion);
                        }
                    } else {
                        log.warn("La relación sede '{}' - facultad '{}'- programa '{}'- espacio '{}' ya existe, se omite",
                                grupoRelacion.getSede().getNombreSede(),
                                grupoRelacion.getFacultad().getNombreFacultad(),
                                grupoRelacion.getEspacioPrograma().getPrograma().getNombre(),
                                grupoRelacion.getEspacioPrograma().getEspacioAcademico().getNombre()
                                );
                    }
                } else {
                    log.warn("Facultad o programa nulo en el registro, se omite");
                }
            } catch (Exception e) {
                log.error("Error al crear el Grupo Relacion: {}", e.getMessage());
            }
        }

        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "crearGrupoRelacionMasivo - Se crearon {} de {} relaciones",
                grupoRelacionCreados.size(), gruposRelacion.size());

        return grupoRelacionCreados;
    } */
   @Override
public List<GrupoRelacion> crearGrupoRelacionMasivo(List<GrupoRelacion> gruposRelacion) {
    log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "crearGrupoRelacionMasivo");

    List<GrupoRelacion> grupoRelacionCreados = new java.util.ArrayList<>();

    for (GrupoRelacion grupoRelacion : gruposRelacion) {
        try {
            if (grupoRelacion.getSede() != null 
                && grupoRelacion.getFacultad() != null 
                && grupoRelacion.getEspacioPrograma() != null
                && grupoRelacion.getEspacioPrograma().getEspacioAcademico() != null
                && grupoRelacion.getEspacioPrograma().getPrograma() != null) {
                
                // ✅ Buscar el EspacioPrograma real en BD
                Optional<EspacioPrograma> espacioProgramaOpt = espacioProgramaRepository
                        .findByEspacioAcademicoAndPrograma(
                            grupoRelacion.getEspacioPrograma().getEspacioAcademico(),
                            grupoRelacion.getEspacioPrograma().getPrograma()
                        );

                if (espacioProgramaOpt.isEmpty()) {
                    log.warn("No existe EspacioPrograma para programa '{}' y espacio '{}', se omite",
                            grupoRelacion.getEspacioPrograma().getPrograma().getNombre(),
                            grupoRelacion.getEspacioPrograma().getEspacioAcademico().getNombre());
                    continue;
                }

                grupoRelacion.setEspacioPrograma(espacioProgramaOpt.get());

                // ✅ Validar si ya existe el GrupoRelacion con sede, facultad y espacioPrograma
                Optional<GrupoRelacion> existente = grupoRelacionRepository
                        .findBySedeAndFacultadAndEspacioPrograma(
                            grupoRelacion.getSede(),
                            grupoRelacion.getFacultad(),
                            grupoRelacion.getEspacioPrograma()
                        );

                if (existente.isEmpty()) {
                    grupoRelacion.setFechaCreacion(new Date());
                    grupoRelacion.setIdUsuarioCreacion(grupoRelacion.getIdUsuarioCreacion());

                    GrupoRelacion newGrupoRelacion = grupoRelacionRepository.save(grupoRelacion);
                    grupoRelacionCreados.add(newGrupoRelacion);

                } else {
                    log.warn("La relación sede '{}' - facultad '{}' - programa '{}' - espacio '{}' ya existe, se omite",
                            grupoRelacion.getSede().getNombreSede(),
                            grupoRelacion.getFacultad().getNombreFacultad(),
                            grupoRelacion.getEspacioPrograma().getPrograma().getNombre(),
                            grupoRelacion.getEspacioPrograma().getEspacioAcademico().getNombre());
                }
            } else {
                log.warn("Algún dato nulo en el registro, se omite");
            }
        } catch (Exception e) {
            log.error("Error al crear el Grupo Relacion: {}", e.getMessage());
        }
    }

    log.info(Constants.MSN_FIN_LOG_INFO + classLog + 
        "crearGrupoRelacionMasivo - Se crearon {} de {} relaciones",
        grupoRelacionCreados.size(), gruposRelacion.size());

    return grupoRelacionCreados;
}

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

