package com.mibanco.novedades.es.gprc;

import com.mibanco.novedades.es.controller.NovedadesController;
import com.mibanco.novedades.es.dao.entity.NovedadesEntity;
import com.mibanco.novedades.es.gen.type.NovedadCDTDigitalType;
import com.mibanco.novedades.es.services.impl.NovedadesClienteCDTImpl;
import com.mibanco.novedades.es.services.impl.NovedadesConsultaDocumentoImpl;
import com.mibanco.novedades.es.utils.ErrorCts;
import com.mibanco.novedades.es.utils.NovedadesCDTDigitalMapperGrpc;
import com.mibanco.novedades.es.utils.NovedadesException;
import com.mibanco.novedadesES.*;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@GrpcService
public class NovedadesGrpcController extends NovedadClienteCDTServiceGrpcGrpc.NovedadClienteCDTServiceGrpcImplBase {

    public static final Logger logger = LoggerFactory.getLogger(NovedadesController.class);

    @Inject
    NovedadesClienteCDTImpl novedadesClienteCDT;

    @Inject
    NovedadesCDTDigitalMapperGrpc novedadesCDTDigitalMapperGrpc;

    @Inject
    NovedadesConsultaDocumentoImpl novedadesConsultaDocumentoImpl;



    @Override
    @Blocking
    public void crearNovedadCDT(CrearNovedadCDTDigitalGrpc request, StreamObserver<Creado> responseObs){

        logger.info("Inicia Creacion Novedad por GRPC");

        NovedadesEntity novedadesEntity = novedadesCDTDigitalMapperGrpc.novedadGrpcToEntity(request);

        try{
            NovedadCDTDigitalType novedadCDTDigitalType = novedadesClienteCDT.crearNovedadCdtDigitalType(novedadesEntity);
            Creado crearNovedadEntidad = Creado.newBuilder()
                    .setNovedadCDTDigital(novedadesCDTDigitalMapperGrpc.novedadGrpcToGrpc(novedadCDTDigitalType)).build();

            responseObs.onNext(crearNovedadEntidad);

            logger.info("Finaliza creacion novedad con GRPC");
            responseObs.onCompleted();
        }catch (NovedadesException e){
            logger.error(ErrorCts.SERVICIO + " Creacion Novedad Grpc");
            throw  new NovedadesException(ErrorCts.SERVICIO + e.getMessage() + " Creacion Novedad Grpc");
        }
    }

    @Override
    @Blocking
    public void consultarNovedad(GetNovedadByDocumentRequest request, StreamObserver<NovedadResponse> responseObs){

        logger.info("Inicia Consulta Novedad por numero de documento mediante GRPC");

        NovedadesEntity novedadesEntity = novedadesCDTDigitalMapperGrpc.consultaNovedadGrpcToEntity(request);

        try{
            List<NovedadCDTDigitalType> novedadCDTDigitalType =
                    novedadesConsultaDocumentoImpl.consultarNovedadesPorNumeroDocumento(novedadesEntity.getNumeroDocumento());

            NovedadResponse consultaNovedadEntidad =
                    NovedadResponse.newBuilder().setNovedadCDTDigital(novedadesCDTDigitalMapperGrpc.consultaNovedadGrpcToGrpc(novedadCDTDigitalType.get(0))).build();

            responseObs.onNext(consultaNovedadEntidad);

            logger.info("Finaliza consulta novedad con GRPC");
            responseObs.onCompleted();
        }catch (NovedadesException e){
            logger.error(ErrorCts.SERVICIO + " Consulta Novedad Grpc");
            throw  new NovedadesException(ErrorCts.SERVICIO + e.getMessage() + " consuñta Novedad Grpc");
        }
    }
}
