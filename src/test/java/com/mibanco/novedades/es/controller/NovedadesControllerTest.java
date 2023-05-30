package com.mibanco.novedades.es.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mibanco.novedades.es.gen.type.NovedadCDTDigitalType;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@QuarkusTest
public class

NovedadesControllerTest {
    @Inject
    ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearNovedadTest() throws IOException{

        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/es-NovedadesES-api.json"));

        String jsonString = new String(jsonData);

        NovedadCDTDigitalType novedadCDTDigitalType = objectMapper.readValue(jsonString, NovedadCDTDigitalType.class);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(novedadCDTDigitalType)
                .when()
                .post("v1/es/novedadCDTDigital")
                .then()
                .statusCode(200);
    }

    @Test
    void consultarNovedadTest() throws IOException{

        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/es-NovedadesES-api.json"));

        String jsonString = new String(jsonData);

        NovedadCDTDigitalType novedadCDTDigitalType = objectMapper.readValue(jsonString, NovedadCDTDigitalType.class);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(novedadCDTDigitalType)
                .when()
                .get("v1/es/novedadCDTDigital?numeroDocumento=7654")
                .then()
                .statusCode(500);
    }
}

