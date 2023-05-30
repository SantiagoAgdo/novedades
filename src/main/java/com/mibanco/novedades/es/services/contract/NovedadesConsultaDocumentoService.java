package com.mibanco.novedades.es.services.contract;

import com.mibanco.novedades.es.gen.type.NovedadCDTDigitalType;

import java.util.List;

public interface NovedadesConsultaDocumentoService {
    List<NovedadCDTDigitalType> consultarNovedadesPorNumeroDocumento(String numero);
}
