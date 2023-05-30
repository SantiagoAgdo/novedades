package com.mibanco.novedades.es.utils;

import com.mibanco.novedadesES.CrearNovedadCDTDigitalGrpc;
import com.mibanco.novedades.es.dao.entity.NovedadesEntity;
import com.mibanco.novedades.es.gen.type.NovedadCDTDigitalType;
import com.mibanco.novedadesES.GetNovedadByDocumentRequest;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class NovedadesCDTDigitalMapperGrpc {

    public NovedadesEntity novedadGrpcToEntity(CrearNovedadCDTDigitalGrpc novedadCDTDigitalGrpc){
        var retorno = new ModelMapper().map(novedadCDTDigitalGrpc, NovedadesEntity.class);
        System.out.println(" ================   respuesta del modelMapper" + retorno + " =============");
        return retorno;
    }

    public NovedadesEntity consultaNovedadGrpcToEntity(GetNovedadByDocumentRequest novedadCDTDigitalGrpc){
        var retorno = new ModelMapper().map(novedadCDTDigitalGrpc, NovedadesEntity.class);
        System.out.println(" ================   respuesta del modelMapper" + retorno + " =============");
        return retorno;
    }

    public CrearNovedadCDTDigitalGrpc novedadGrpcToGrpc(NovedadCDTDigitalType novedadCDTDigitalType){
        return new ModelMapper().map(novedadCDTDigitalType, CrearNovedadCDTDigitalGrpc.class);
    }

    public GetNovedadByDocumentRequest consultaNovedadGrpcToGrpc(NovedadCDTDigitalType novedadCDTDigitalType){
        return new ModelMapper().map(novedadCDTDigitalType, GetNovedadByDocumentRequest.class);
    }

}
