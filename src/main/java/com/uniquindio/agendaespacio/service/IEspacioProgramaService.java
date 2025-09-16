package com.uniquindio.agendaespacio.service;



import com.uniquindio.agendaespacio.entity.EspacioAcademico;
import com.uniquindio.agendaespacio.entity.EspacioPrograma;
import com.uniquindio.agendaespacio.entity.Programa;

import java.util.List;

public interface IEspacioProgramaService {

    List<EspacioPrograma> crearEspacioPrograma(List<EspacioPrograma> listaFp);
    List<EspacioPrograma> listarEspacioPrograma();
    List<EspacioPrograma> listarEspacioProgramaPorPrograma(Programa programa);
    void eliminarEspacioPrograma(EspacioAcademico espacioAcademico);
    void eliminarEspaPrograma(Integer idEspacioPrograma);
    List<EspacioPrograma> crearEspacioProgramasMasivo(List<EspacioPrograma> espacioProgramas);

}
