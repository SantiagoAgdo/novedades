package com.mibanco.novedades.es.dao.contract;

import com.mibanco.novedades.es.dao.entity.NovedadesEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class NovedadClienteCDTDao implements INovedadesClienteCDTDao{

    List<NovedadesEntity> novedad = new ArrayList<>();

    public void crearNovedad(NovedadesEntity novedadesEntity){
        novedad.add(novedadesEntity);
    }

    public List<NovedadesEntity> getNovedad(String numeroDeDocumento){
        return (List<NovedadesEntity>) novedad.stream().filter(x -> x.getNumeroDocumento().contains(numeroDeDocumento));
    }
}
