package com.uniquindio.agendaespacio.service.imp;

import com.uniquindio.agendaespacio.entity.Rol;
import com.uniquindio.agendaespacio.repository.RolRepository;
import com.uniquindio.agendaespacio.service.IRolService;
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
public class RolService implements IRolService {

    @Autowired
    private RolRepository rolRepository;

    private final String classLog = getClass().getName() + '.';

    @Override
    public Rol crearRol(Rol rol) {
        log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"crear rol");
        if(Validation.isNullOrEmpty(rolRepository.findByNombreIgnoreCase(rol.getNombre()))){
            rol.setNombre(rol.getNombre());
            rol.setDescripcion(rol.getDescripcion());
            rol.setFechaCreacion(new Date());
            rol.setIdUsuarioCreacion(rol.getIdUsuarioCreacion());
            Rol newRol = rolRepository.save(rol);
            if(!Validation.isNullOrEmpty(newRol)) {
                return newRol;
            }
            log.error(Constants.MSN_FIN_LOG_INFO+classLog+ "No se puede crear el rol");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se puede crear el rol");
        }
        log.info(Constants.MSN_FIN_LOG_INFO+classLog + "Rol creado");
        throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "El rol ya  esta  creado");
    }

    @Override
    public List<Rol> listarRol() {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "listar Rol");
        List<Rol> listRol = rolRepository.findAll();
        if (!Validation.isNullOrEmpty(listRol)) {
            return listRol;
        }
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "listar Rol");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lista de roles esta vacía");
    }

    @Override
    public Rol actualizarRol(Rol rol) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "actualizarRol");
        Optional<Rol> rolExiste = rolRepository.findById(rol.getIdRol());
        if (rolExiste.isPresent()){
            if (!Validation.isNullOrEmpty(rolRepository.findOneByNombreAndIdRolNot(rol.getNombre(), rol.getIdRol()))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El rol ya existe");
            }
        }

        rolExiste.get().setNombre(rol.getNombre());
        rolExiste.get().setDescripcion(rol.getDescripcion());
        rolExiste.get().setIdUsuarioCreacion(rol.getIdUsuarioCreacion());
        rolExiste.get().setIdUsuarioModificacion(rol.getIdUsuarioCreacion());
        rolExiste.get().setFechaModificacion(new Date());

        Rol rolCreado = rolRepository.save(rolExiste.get());

        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "actuaizarRol");
        return rolCreado;
    }

    @Override
    public void eliminarRol(Integer idRol) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "eliminar Rol");
        Optional<Rol> lista = rolRepository.findById(idRol);
        if (lista.isEmpty()) {
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminar rol");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " El rol  no existe");
        }
        rolRepository.delete(lista.get());
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminar Rol");
    }




}
