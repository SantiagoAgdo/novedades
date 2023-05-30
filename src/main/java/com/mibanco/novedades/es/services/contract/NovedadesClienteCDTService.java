package com.mibanco.novedades.es.services.contract;

import com.mibanco.novedades.es.dao.entity.NovedadesEntity;
import com.mibanco.novedades.es.gen.type.NovedadCDTDigitalType;


public interface NovedadesClienteCDTService {

    NovedadCDTDigitalType crearNovedadCdtDigitalType(NovedadesEntity novedadesEntity);

}
