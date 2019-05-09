package com.adminneg.bussines.administrar.negocio.adminneg.api;

import com.adminneg.bussines.administrar.negocio.adminneg.model.Adm_01usuario;
import com.adminneg.bussines.administrar.negocio.adminneg.modelws.WsAdm_01usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by albertopalomarrobledo on 1/12/18.
 */

public interface WebServiceApi {

//    USUARIOS //put:adicionar
//@Headers("X-API-KEY:123456")
    @PUT("restful_negociobd/index.php/usuarios/usuario")
    Call<Void> registrarUsuario(@Body Adm_01usuario usuario);

    @PUT("restful_negociobd/index.php/usuarios/login")
    Call<List<Adm_01usuario>> login(@Body Adm_01usuario usuario);

    @PUT("restful_negociobd/index.php/usuarios/login2")
    Call<List<WsAdm_01usuario>> login2(@Body Adm_01usuario usuario);

    @POST("/api/login")
    Call<List<Adm_01usuario>> login3(@Body Adm_01usuario usuario);

    @DELETE("api/delete/{id}")
    Call<Void> deleteById(@Path("id") Long id);

    @PUT("api/update_sql")
    Call<Adm_01usuario> update(@Body Adm_01usuario profesor);

    @GET("api/profesores")
    Call<List<Adm_01usuario>> getUsuarios();


}
