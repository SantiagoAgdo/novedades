package com.mibanco.novedades.es.services.impl;

import com.mibanco.novedades.es.controller.NovedadesController;
import com.mibanco.novedades.es.dao.contract.NovedadClienteCDTDao;
import com.mibanco.novedades.es.dao.entity.NovedadesEntity;
import com.mibanco.novedades.es.gen.type.NovedadCDTDigitalType;
import com.mibanco.novedades.es.services.contract.NovedadesClienteCDTService;
import com.mibanco.novedades.es.utils.ErrorCts;
import com.mibanco.novedades.es.utils.NovedadesException;
import com.mibanco.novedades.es.utils.NovedadesMapper;
import com.mibanco.novedades.es.utils.NovedadesValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class NovedadesClienteCDTImpl implements NovedadesClienteCDTService {

    public static final Logger logger = LoggerFactory.getLogger(NovedadesController.class);

    @Inject
    NovedadClienteCDTDao novedadClienteCDTDao;

    @Inject
    NovedadesMapper novedadesMapper;

    @Inject
    NovedadesValidator novedadesValidator;

    @Override
    @Transactional
    public NovedadCDTDigitalType crearNovedadCdtDigitalType(NovedadesEntity novedadesEntity) {

        logger.info("Inicia creacion de crearNovedadCdtDigitalTypeImpl");

        try {
            NovedadCDTDigitalType novedadCDTDigitalType = novedadesMapper.novedadCDTDigitalToType(novedadesEntity);
            novedadesValidator.verificarNovedadesCDT(novedadCDTDigitalType);

            novedadClienteCDTDao.crearNovedad(novedadesEntity);

            logger.info("Termina creacion de crearNovedadCdtDigitalTypeImpl");
            return  novedadCDTDigitalType;
        }catch (NovedadesException e){
            logger.error(ErrorCts.OUTPUT_CLIENTES + " en NovedadesClienteCDTImpl exception: " + e.getMessage());
            throw new NovedadesException(ErrorCts.SERVICIO + e.getMessage() + "  en NovedadesClienteCDTImpl ");
        }
    }

}
