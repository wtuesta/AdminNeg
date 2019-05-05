package com.adminneg.bussines.administrar.negocio.adminneg.modelws;

import com.adminneg.bussines.administrar.negocio.adminneg.model.Adm_01usuario;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsAdm_01usuario {
    private Boolean err;
    private int nroError;
    private String mensaje;
    private String errores;

    private List<Adm_01usuario> registro;


    public WsAdm_01usuario(){}

    public WsAdm_01usuario(Boolean err, int nroError, String mensaje, String errores, List<Adm_01usuario> registro) {
        this.err = err;
        this.nroError = nroError;
        this.mensaje = mensaje;
        this.errores = errores;
        this.registro = registro;
    }

    public Boolean getErr() {
        return err;
    }

    public void setErr(Boolean err) {
        this.err = err;
    }

    public int getNroError() {
        return nroError;
    }

    public void setNroError(int nroError) {
        this.nroError = nroError;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getErrores() {
        return errores;
    }

    public void setErrores(String errores) {
        this.errores = errores;
    }

    public List<Adm_01usuario> getRegistro() {
        return registro;
    }

    public void setRegistro(List<Adm_01usuario> registro) {
        this.registro = registro;
    }
}
