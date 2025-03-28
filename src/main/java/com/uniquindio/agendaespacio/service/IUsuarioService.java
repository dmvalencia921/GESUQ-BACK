package com.uniquindio.agendaespacio.service;

import com.uniquindio.agendaespacio.entity.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario crearUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
    Usuario actualizarUsuario(Usuario usuario);
    Usuario buscarUsuarioPorUsuario(String usuario);
    void eliminarUsuario(Integer idUsuario);
}
