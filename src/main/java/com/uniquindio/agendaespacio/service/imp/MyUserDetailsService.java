package com.uniquindio.agendaespacio.service.imp;

import com.uniquindio.agendaespacio.entity.Usuario;
import com.uniquindio.agendaespacio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario opUser = usuarioRepository.findByUsuario(username);
        if (opUser == null) {
            throw new UsernameNotFoundException("Usuario no encontrado" + username);
        }

        Usuario user = opUser;
        // Obtener la lista de roles del usuario
        List<GrantedAuthority> authorities = user.getListaRol().stream()
                .map(usuarioRol -> new SimpleGrantedAuthority(usuarioRol.getNombre()))
                .collect(Collectors.toList());

        return User.builder()
                .username(user.getUsuario())
                .password(user.getClave())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .build();
    }


}
