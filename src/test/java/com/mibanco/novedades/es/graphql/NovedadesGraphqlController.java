package com.mibanco.novedades.es.graphql;

import com.mibanco.novedades.es.dao.entity.NovedadesEntity;
import com.mibanco.novedades.es.gen.type.NovedadCDTDigitalType;
import com.mibanco.novedades.es.services.impl.NovedadesConsultaDocumentoImpl;
import com.mibanco.novedades.es.utils.NovedadesMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class NovedadesGraphqlController {

    @Mock
    private NovedadesConsultaDocumentoImpl novedadesConsultaDocumento;


    @Mock
    private NovedadesMapper novedadesMapper;

    @InjectMocks
    private NovedadesGraphlController NovedadCDTDigitalType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testObtenerClientesCDTDigital() {
        // Mock de datos
        List<NovedadCDTDigitalType> novedadesMock = new ArrayList<>();
        // Agrega los datos de prueba a 'clientesMock'

        // Define el comportamiento esperado del servicio y el mapper
        when(novedadesConsultaDocumento.consultarNovedadesPorNumeroDocumento("7654")).thenReturn(novedadesMock);
        when(novedadesMapper.novedadTypeToEntity(novedadesMock.get(0))).thenReturn(new NovedadesEntity());
        when(novedadesMapper.novedadCDTDigitalToType(any(NovedadesEntity.class))).thenReturn(new NovedadCDTDigitalType());

        // Llama al método del controlador que deseas probar
        List<NovedadCDTDigitalType> result = NovedadCDTDigitalType.getNovedadPorDocuemnto("7654");

        // Verifica los resultados
        assertEquals(novedadesMock.size(), result.size());
    }

}
