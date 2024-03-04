package com.NatanielSanchez.SistemaReporteIncidentes.repositories;

import com.NatanielSanchez.SistemaReporteIncidentes.models.Tecnico;

import java.util.List;

public interface TecnicoRepositoryCustom
{
    List<Tecnico> getTecnicosFiltrados(String nombre, String apellido, List<Long> idEspecialidades);
}
