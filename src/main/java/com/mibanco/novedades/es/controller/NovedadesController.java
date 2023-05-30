package com.mibanco.novedades.es.controller;

import com.mibanco.novedades.es.dao.entity.NovedadesEntity;
import com.mibanco.novedades.es.gen.contract.V1NovedadesESApi;
import com.mibanco.novedades.es.gen.type.NovedadCDTDigitalType;
import com.mibanco.novedades.es.services.impl.NovedadesClienteCDTImpl;
import com.mibanco.novedades.es.services.impl.NovedadesConsultaDocumentoImpl;
import com.mibanco.novedades.es.utils.ErrorCts;
import com.mibanco.novedades.es.utils.NovedadesException;
import com.mibanco.novedades.es.utils.NovedadesMapper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class NovedadesController implements V1NovedadesESApi {

    public static final Logger logger = LoggerFactory.getLogger(NovedadesController.class);

    @Inject
    NovedadesClienteCDTImpl novedadesClienteCDT;

    @Inject
    NovedadesConsultaDocumentoImpl novedadesConsultaDocumentoImpl;

    @Inject
    NovedadesMapper novedadesMapper;

    @Override
    public List<NovedadCDTDigitalType> crearNovedadClienteCDT(NovedadCDTDigitalType novedadCDTDigitalType) {
        logger.info("Inicia crearNovedadClienteCDTController");

        try{
            NovedadesEntity novedadesEntity = novedadesMapper.novedadTypeToEntity(novedadCDTDigitalType);
            novedadCDTDigitalType = novedadesClienteCDT.crearNovedadCdtDigitalType(novedadesEntity);

            logger.info("Finaliza crearNovedadClienteCDTController");
            return List.of(novedadCDTDigitalType);
        }catch (NovedadesException novedadesException){
            logger.error(ErrorCts.SERVICIO + " crearNovedadClienteCDTController exception: " + novedadesException.getMessage());
            throw  new NovedadesException(ErrorCts.SERVICIO + novedadesException.getMessage() + " En crearNovedadClienteCDTController");
        }

    }

    @Override
    public List<NovedadCDTDigitalType> v1EsNovedadCDTDigitalGet(String numeroDocumento) {
        logger.info("Inicia consultarNovedadesPorNumeroDocumentoController");

        try{
            List<NovedadCDTDigitalType> list = novedadesConsultaDocumentoImpl.consultarNovedadesPorNumeroDocumento(numeroDocumento);
            logger.info("Finaliza consultarNovedadesPorNumeroDocumentoController");
            return list;
        }catch (NovedadesException novedadesException){
            logger.error(ErrorCts.SERVICIO + " consultarNovedadesPorNumeroDocumentoController exception: " + novedadesException.getMessage());
            throw  new NovedadesException(ErrorCts.SERVICIO + novedadesException.getMessage() + " En consultarNovedadesPorNumeroDocumentoController");
        }
    }
}
