package com.uniquindio.agendaespacio.service.imp;

import com.uniquindio.agendaespacio.entity.*;
import com.uniquindio.agendaespacio.repository.ReservaEspacioRepository;
import com.uniquindio.agendaespacio.service.IReservaEspacioService;
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
public class ReservaEspacioService implements IReservaEspacioService {

    @Autowired
    private ReservaEspacioRepository espacioRepository;

    private final String classLog = getClass().getName() + '.';

    /**
     * Metodo que permite crear la reserva del espacio academico
     * 
     * @param reserva a realizar
     * @return la reserva realzada
     */
    @Override
    public ReservaEspacio crearReservaEspacio(ReservaEspacio reserva) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "crearReservaEspacio");
        log.info("Reserva recibida: " + reserva);

        if (reserva == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El objeto reserva es nulo");
        }

        if (reserva.getGrupoRelacion() == null || reserva.getGrupoRelacion().getIdGrupoRelacion() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "GrupoRelacion o su ID es nulo");
        }

        if (reserva.getFechaReservaEspacio() == null || reserva.getHorario() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fecha o horario son nulos");
        }

        if (!espacioRepository.existsReserva(
                reserva.getGrupoRelacion().getIdGrupoRelacion(),
                reserva.getFechaReservaEspacio(),
                reserva.getHorario())) {

            reserva.setFechaCreacion(new Date());
            ReservaEspacio nuevaReserva = espacioRepository.save(reserva);

            if (nuevaReserva != null) {
                return nuevaReserva;
            }

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo crear la reserva");
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La reserva ya existe");
    }

    /**
     * Metodo que permite listar las reservas de los espacios
     * 
     * @return lista de reservas
     */
    @Override
    public List<ReservaEspacio> listarReservas() {
        List<ReservaEspacio> listaReserva = espacioRepository.findAll();
        if (!Validation.isNullOrEmpty(listaReserva)) {
            return listaReserva;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lista de reserva esta vacia");
    }

    @Override
    public ReservaEspacio actualizarReserva(ReservaEspacio reservaEspacio) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "actualizarReserva");
        ReservaEspacio reserva = espacioRepository.findOneByIdReservaEspacio(reservaEspacio.getIdReservaEspacio());
        if (!Validation.isNullOrEmpty(reservaEspacio)) {
            reserva.setOcupado(reservaEspacio.isOcupado());
            reserva.setFechaCreacion(new Date());
            reserva.setIdUsuarioModificacion(reservaEspacio.getIdUsuarioModificacion());
            espacioRepository.save(reserva);
            eliminarReserva(reservaEspacio.getIdReservaEspacio());
            return reserva;
        }
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "actualizarReserva");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La reserva no existe");
    }

    /**
     * Metodo que permite listar reservas por el id del usuario
     * 
     * @param usuario a buscar la reserva
     * @return reservas del usuario
     */
    @Override
    public List<ReservaEspacio> listarReservaPorUsuario(Usuario usuario) {
        List<ReservaEspacio> lista = espacioRepository.findByUsuario(usuario);
        if (!Validation.isNullOrEmpty(lista)) {
            return lista;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lista de reserva esta vacia");
    }

    @Override
    public void eliminarReserva(Integer idEspacioAcademico) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "eliminarReserva");
        Optional<ReservaEspacio> lista = espacioRepository.findById(idEspacioAcademico);
        if (lista.isEmpty()) {
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarReserva");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La reserva no existe");
        }
        espacioRepository.deleteById(idEspacioAcademico);
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarReserva");
    }

}
