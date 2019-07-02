package com.adminneg.bussines.administrar.negocio.adminneg.modelws;

import com.adminneg.bussines.administrar.negocio.adminneg.model.Maest_04pais_moneda;

import java.util.List;

public class WsMaest_04pais_moneda {
    private int status;
    private int err;
    private List<Maest_04pais_moneda> result;

    public WsMaest_04pais_moneda(){}

    public WsMaest_04pais_moneda(int status, int err, List<Maest_04pais_moneda> result) {
        this.status = status;
        this.err = err;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public List<Maest_04pais_moneda> getResult() {
        return result;
    }

    public void setResult(List<Maest_04pais_moneda> result) {
        this.result = result;
    }

}
