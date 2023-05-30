package com.mibanco.novedades.es.grpc;

import com.mibanco.novedades.es.dao.entity.NovedadesEntity;
import com.mibanco.novedades.es.gen.type.NovedadCDTDigitalType;
import com.mibanco.novedades.es.gprc.NovedadesGrpcController;
import com.mibanco.novedades.es.services.impl.NovedadesClienteCDTImpl;
import com.mibanco.novedades.es.utils.NovedadesCDTDigitalMapperGrpc;
import com.mibanco.novedadesES.Creado;
import com.mibanco.novedadesES.CrearNovedadCDTDigitalGrpc;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.ArgumentMatchers.any;

public class NovedadesGrpcControllerTest {


    @Mock
    private NovedadesClienteCDTImpl novedadesClienteCDTImlp;

    @Mock
    private NovedadesCDTDigitalMapperGrpc novedadesCDTDigitalMapperGrpc;

    @InjectMocks
    private NovedadesGrpcController novedadesGrpcController;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearNovedad(){
        CrearNovedadCDTDigitalGrpc requestMock = CrearNovedadCDTDigitalGrpc.newBuilder().build();
        NovedadCDTDigitalType novedadCDTDigitalTypeMock = new NovedadCDTDigitalType();
        Creado rptMock = Creado.newBuilder().setNovedadCDTDigital(requestMock).build();
        StreamObserver<Creado> responseObsMock = Mockito.mock(StreamObserver.class);

        Mockito.when(novedadesCDTDigitalMapperGrpc.novedadGrpcToEntity(requestMock)).thenReturn(new NovedadesEntity());
        Mockito.when(novedadesCDTDigitalMapperGrpc.novedadGrpcToGrpc(novedadCDTDigitalTypeMock)).thenReturn(requestMock);
        Mockito.when(novedadesClienteCDTImlp.crearNovedadCdtDigitalType(any(NovedadesEntity.class))).thenReturn(novedadCDTDigitalTypeMock);

        novedadesGrpcController.crearNovedadCDT(requestMock, responseObsMock);

        Mockito.verify(responseObsMock, Mockito.times(1)).onNext(rptMock);
        Mockito.verify(responseObsMock, Mockito.times(1)).onCompleted();
    }


}
