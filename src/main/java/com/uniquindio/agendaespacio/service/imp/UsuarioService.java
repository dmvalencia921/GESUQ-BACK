package com.uniquindio.agendaespacio.service.imp;

import com.uniquindio.agendaespacio.entity.Usuario;
import com.uniquindio.agendaespacio.repository.UsuarioRepository;
import com.uniquindio.agendaespacio.service.IUsuarioService;
import com.uniquindio.agendaespacio.util.constants.Constants;
import com.uniquindio.agendaespacio.util.utilities.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final String classLog = getClass().getName() + '.';


    @Override
    public Usuario crearUsuario(Usuario usuario) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "crearUsuario");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Optional<Usuario> usuarioExite = usuarioRepository.findByUsuarioIgnoreCase(usuario.getUsuario());
        if (usuarioExite.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario ya existe");
        }
        String passwordCifrado = passwordEncoder.encode(usuario.getClave());
        Usuario usuarioCrear = new Usuario();
        if (usuario.getUsuario().contains("@uniquindio.edu.co")) {
            usuarioCrear.setUsuario(usuario.getUsuario());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El usuario debe pertenecer al dominio @uniquidio.edu.co");
        }
        usuarioCrear.setNombres(usuario.getNombres());
        usuarioCrear.setApellidos(usuario.getApellidos());
        usuarioCrear.setClave(passwordCifrado);
        usuarioCrear.setActivo(usuario.isActivo());
        usuarioCrear.setIdUsuarioCreacion(usuario.getIdUsuarioCreacion());

        Usuario usuarioCreado = usuarioRepository.save(usuarioCrear);

        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "crearUsuario");
        return usuarioCreado;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "listarUsuarios");
        List<Usuario> usuarioList = usuarioRepository.findAll();
        if (!Validation.isNullOrEmpty(usuarioList)) {
            return usuarioList;
        }
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "listarUsuarios");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lista de usuarios esta vacía");
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "actualizarUsuario");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<Usuario> usuarioExite = usuarioRepository.findById(usuario.getIdUsuario());
        if (usuarioExite.isPresent()) {
            if (!Validation.isNullOrEmpty(
                    usuarioRepository.findOneByUsuarioAndIdUsuarioNot(usuario.getUsuario(), usuario.getIdUsuario()))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario ya existe");
            }
        }
        String passwordCifrado = passwordEncoder.encode(usuario.getClave());
        usuarioExite.get().setNombres(usuario.getNombres());
        usuarioExite.get().setApellidos(usuario.getApellidos());
        usuarioExite.get().setUsuario(usuario.getUsuario());
        usuarioExite.get().setClave(passwordCifrado);
        usuarioExite.get().setActivo(usuario.isActivo());
        usuarioExite.get().setFechaModificacion(new Date());
        usuarioExite.get().setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());

        Usuario usuarioCreado = usuarioRepository.save(usuarioExite.get());


        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "actualizarUsuario");
        return usuarioCreado;
    }

    @Override
    public Usuario buscarUsuarioPorUsuario(String usuario) {
        Usuario usuarioExistente = usuarioRepository.findByUsuario(usuario);
        if (!Validation.isNullOrEmpty(usuarioExistente)) {
            return usuarioExistente;
        }
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "buscarUsuarioPorUsuario");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no exste");
    }

    @Override
    public void eliminarUsuario(Integer idUsuario) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "eliminarUsuario");
        Optional<Usuario> lista = usuarioRepository.findById(idUsuario);
        if (lista.isEmpty()) {
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminar usuario");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " El usuario  no existe");
        }
        usuarioRepository.delete(lista.get());
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarUsuario");
    }
}
