package com.uniquindio.agendaespacio.service.imp;

import com.uniquindio.agendaespacio.entity.Programa;
import com.uniquindio.agendaespacio.repository.ProgramaRepository;
import com.uniquindio.agendaespacio.service.IProgramaService;
import com.uniquindio.agendaespacio.util.constants.Constants;
import com.uniquindio.agendaespacio.util.utilities.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProgramaService implements IProgramaService {

    @Autowired
    private ProgramaRepository programaRepository;

    private final String classLog = getClass().getName() + '.';

    /**
     * Metodo encargado de crear in programa
     * @param programa programa a crear
     * @return programa creado
     */
    @Override
    public Programa crearPrograma(Programa programa) {
       log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"Crear programa");
       if(Validation.isNullOrEmpty(programaRepository.findByNombreIgnoreCase(programa.getNombre()))) {
           programa.setNombre(programa.getNombre());
           programa.setFechaCreacion(new Date());
           programa.setIdUsuarioCreacion(programa.getIdUsuarioCreacion());
           Programa newPrograma = programaRepository.save(programa);

           if(!Validation.isNullOrEmpty(newPrograma)) {
               return newPrograma;
           }
           log.error(Constants.MSN_INICIO_LOG_INFO+classLog+"No se puede crear el programa");
           throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se puede crear el programa");
       }
        log.info(Constants.MSN_FIN_LOG_INFO+classLog+"Crear programa");
       throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El programa ya existe");
    }


    /**
     * Metodo que permite la actualización de un programa
     * @param programa a actuailzar
     * @return programa actualizado
     */
    @Override
    public Programa actualizarPrograma(Programa programa) {
        log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"Actualizar programa");
        Optional<Programa> lista = programaRepository.findById(programa.getIdPrograma());
        if(lista.isPresent()) {
            if(Validation.isNullOrEmpty(programaRepository.findOneByNombreAndIdProgramaNot(programa.getNombre(),programa.getIdPrograma()))){
                programa.setNombre(programa.getNombre());
                programa.setFechaCreacion(new Date());
                programa.setIdUsuarioCreacion(programa.getIdUsuarioCreacion());
                programaRepository.save(programa);
                return programa;
            }
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "No se pudo actualizar el programa");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El programa ya existe");
        }
        log.info(Constants.MSN_FIN_LOG_INFO+classLog+"Actualizar programa");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El programa no existe");
    }

    /**
     * Metodo que permite listar los programas
     * @return lista de programas
     */
    @Override
    public List<Programa> listarProgramas() {
       List<Programa> listProgramas = programaRepository.findAll();
       if(!Validation.isNullOrEmpty(listProgramas)) {
           return listProgramas;
       }
       throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Lista de programas vacia");
    }

    @Override
    public void eliminarPrograma(Integer idPrograma) {
        log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"Eliminar programa");
        Optional<Programa> lista = programaRepository.findById(idPrograma);
        if(lista.isEmpty()){
            log.info(Constants.MSN_FIN_LOG_INFO+classLog+"eliminar programa");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El programa no existe");
        }
        programaRepository.deleteById(idPrograma);
        log.info(Constants.MSN_FIN_LOG_INFO+classLog+"Elimina programa");
    }

    @Override
    public List<Programa> crearProgramasMasivo(List<Programa> programas) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "crearProgramasMasivo");
        
        List<Programa> programasCreados = new java.util.ArrayList<>();
        
        for (Programa programa : programas) {
            try {
                // Verificar si el programa ya existe por nombre
                if (Validation.isNullOrEmpty(programaRepository.findByNombreIgnoreCase(programa.getNombre()))) {
                    programa.setNombre(programa.getNombre());
                    programa.setCodPrograma(programa.getCodPrograma());
                    programa.setFechaCreacion(new Date());
                    programa.setIdUsuarioCreacion(programa.getIdUsuarioCreacion());
                    Programa newPrograma = programaRepository.save(programa);
                    
                    if (!Validation.isNullOrEmpty(newPrograma)) {
                        programasCreados.add(newPrograma);
                    }
                } else {
                    log.warn("El programa con nombre '{}' ya existe, se omite", programa.getNombre());
                }
            } catch (Exception e) {
                log.error("Error al crear el programa '{}': {}", programa.getNombre(), e.getMessage());
            }
        }
        
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "crearProgramasMasivo - Se crearon {} de {} programas", 
                programasCreados.size(), programas.size());
        
        return programasCreados;
    }
}
