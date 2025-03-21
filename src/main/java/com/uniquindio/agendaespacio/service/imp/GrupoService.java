package com.uniquindio.agendaespacio.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uniquindio.agendaespacio.entity.Grupo;
import com.uniquindio.agendaespacio.repository.GrupoRepository;
import com.uniquindio.agendaespacio.service.IGrupoService;
import com.uniquindio.agendaespacio.util.constants.Constants;
import com.uniquindio.agendaespacio.util.utilities.Validation;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GrupoService implements IGrupoService {


    @Autowired
    private GrupoRepository grupoRepository;

    private final String classLog= getClass().getName() + ".";


    /**
     * Metodo que permite crear el grupo
     * 
     * @param grupo que se desea crear
     * @return el grupo
     */

    @Override
    public Grupo crearGrupo(Grupo grupo){

        log.info(Constants.MSN_INICIO_LOG_INFO+ classLog + "crearGrupo");
        
        if (Validation.isNullOrEmpty(grupoRepository.findByNombreGrupoIgnoreCase(grupo.getNombreGrupo()))) {
            
            grupo.setNombreGrupo(grupo.getNombreGrupo());
            grupo.setSemestre(grupo.getSemestre());
            Grupo newGrupo=grupoRepository.save(grupo);

            if (!Validation.isNullOrEmpty(newGrupo)) {
            
                return newGrupo;
            }
            log.error(Constants.MSN_INICIO_LOG_INFO+ classLog + "No se pudo crear el grupo");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El grupo ya existe");
        }

    log.info(Constants.MSN_FIN_LOG_INFO+classLog+"crearGrupo");
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El grupo ya existe");


    }

        /**
     * Metodo que permite actualizar una grupo
     * @param grupo a actualizar
     * @return grupo actualizada
     */

     @Override
     public Grupo actualizarGrupo(Grupo grupo){

        log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"actualizarGrupo");
        Optional<Grupo>lista=grupoRepository.findById(grupo.getIdGrupo());
        if (lista.isPresent()) {
            if (Validation.isNullOrEmpty(grupoRepository.findOneByNombreGrupoAndIdGrupoNot(grupo.getNombreGrupo(),grupo.getIdGrupo()))) {
                
                grupo.setNombreGrupo(grupo.getNombreGrupo());
                grupo.setSemestre(grupo.getSemestre());
                grupoRepository.save(grupo);
                return grupo;
            }
            log.info(Constants.MSN_FIN_LOG_INFO+classLog+"No se pudo actualizar el grupo");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El grupo ya existe");
        }
        log.info(Constants.MSN_FIN_LOG_INFO+classLog+"actualizarGrupo");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El registro no existe");
     }

     @Override
     public List<Grupo> listaGrupos(){
        List<Grupo> listaGrupo=grupoRepository.findAll();
        if (!Validation.isNullOrEmpty(listaGrupo)) {
            return listaGrupo;
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No hay grupos");

     }

        @Override
        public void eliminarGrupo(Integer idGrupo){

            log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"eliminarGrupo");
            Optional<Grupo>lista=grupoRepository.findById(idGrupo);
            if (lista.isEmpty()) {
                log.info(Constants.MSN_FIN_LOG_INFO+classLog+"eliminarGrupo");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El grupo no existe");
            }
            grupoRepository.deleteById(idGrupo);
        }



}
