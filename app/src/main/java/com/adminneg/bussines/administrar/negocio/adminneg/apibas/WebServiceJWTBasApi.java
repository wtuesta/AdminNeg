package com.adminneg.bussines.administrar.negocio.adminneg.apibas;

import com.adminneg.bussines.administrar.negocio.adminneg.model.Adm_01usuario;
import com.adminneg.bussines.administrar.negocio.adminneg.modelws.Param;
import com.adminneg.bussines.administrar.negocio.adminneg.modelws.WSBody;
import com.adminneg.bussines.administrar.negocio.adminneg.modelws.WsAdm_01usuario;
import com.adminneg.bussines.administrar.negocio.adminneg.modelws.WsMaest_04pais_moneda;
import com.adminneg.bussines.administrar.negocio.adminneg.modelws.WsTest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WebServiceJWTBasApi {
    //@POST("/wsbasic.php/")
    // Call<List<Adm_01usuario>> login3(@Body Adm_01usuario usuario);

    @POST("/api_admineg/wsbasic.php/")
    Call<List<WsMaest_04pais_moneda>> obtenerPaises(@Header("Authorization") String authHeader, @Body WSBody body);

    @POST("/token")
    Call<List<String>> obtenerToken(@Body WsTest login);

    @GET("/api/acceso_solo_con_jwt")
    Call<List<String>> obtenerMovimientosBancarios(@Header("Authorization") String authHeader);
}

