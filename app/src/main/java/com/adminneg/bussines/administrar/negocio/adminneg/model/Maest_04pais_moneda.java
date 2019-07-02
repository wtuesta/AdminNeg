package com.adminneg.bussines.administrar.negocio.adminneg.model;

public class Maest_04pais_moneda {
    private String codPais;
    private String pais;
    private String moneda;
    private String code;
    private String simboloMoneda;
    private Long decimales;
    private String etiquetaImpuestoVenta;
    private Double porcentajeImpuestoVentas;
    private String codIdioma;
    private String formatoFecha;

    public Maest_04pais_moneda(){

    }
    public Maest_04pais_moneda(String codPais, String pais, String moneda, String code, String simboloMoneda, Long decimales, String etiquetaImpuestoVenta, Double porcentajeImpuestoVentas, String codIdioma, String formatoFecha, String flagActivo) {
        this.codPais = codPais;
        this.pais = pais;
        this.moneda = moneda;
        this.code = code;
        this.simboloMoneda = simboloMoneda;
        this.decimales = decimales;
        this.etiquetaImpuestoVenta = etiquetaImpuestoVenta;
        this.porcentajeImpuestoVentas = porcentajeImpuestoVentas;
        this.codIdioma = codIdioma;
        this.formatoFecha = formatoFecha;
        this.flagActivo = flagActivo;
    }

    private String flagActivo;

    public String getCodPais() {
        return codPais;
    }

    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSimboloMoneda() {
        return simboloMoneda;
    }

    public void setSimboloMoneda(String simboloMoneda) {
        this.simboloMoneda = simboloMoneda;
    }

    public Long getDecimales() {
        return decimales;
    }

    public void setDecimales(Long decimales) {
        this.decimales = decimales;
    }

    public String getEtiquetaImpuestoVenta() {
        return etiquetaImpuestoVenta;
    }

    public void setEtiquetaImpuestoVenta(String etiquetaImpuestoVenta) {
        this.etiquetaImpuestoVenta = etiquetaImpuestoVenta;
    }

    public Double getPorcentajeImpuestoVentas() {
        return porcentajeImpuestoVentas;
    }

    public void setPorcentajeImpuestoVentas(Double porcentajeImpuestoVentas) {
        this.porcentajeImpuestoVentas = porcentajeImpuestoVentas;
    }

    public String getCodIdioma() {
        return codIdioma;
    }

    public void setCodIdioma(String codIdioma) {
        this.codIdioma = codIdioma;
    }

    public String getFormatoFecha() {
        return formatoFecha;
    }

    public void setFormatoFecha(String formatoFecha) {
        this.formatoFecha = formatoFecha;
    }

    public String getFlagActivo() {
        return flagActivo;
    }

    public void setFlagActivo(String flagActivo) {
        this.flagActivo = flagActivo;
    }



}
