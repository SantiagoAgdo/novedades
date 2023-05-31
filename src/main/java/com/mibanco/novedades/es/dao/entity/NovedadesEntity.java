package com.mibanco.novedades.es.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class NovedadesEntity {

    private String id;
    private String numeroDocumento;
    private String codigoNovedad;
    private String descripcionNovedad;
    private Date fechaNovedad;
}
