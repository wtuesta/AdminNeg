package com.adminneg.bussines.administrar.negocio.adminneg.model;

public class Adm_01usuario {
    private Long idUsuario;
    private String nombre;
    private String email;
    private String password;
    private String tipoUsuario;
    private String pais;
    private String idioma;
    private String rol1;
    private String rol2;
    private String rol3;
    private String rol4;
    private String rol5;
    private String foto;
    private String flagActivo;
    private Long creacionFecha;
    private Long creacionUsuario;
    private Long modificacionFecha;
    private Long modificacionUsuario;

    public Adm_01usuario(){

    }

    public Adm_01usuario(Long idUsuario, String nombre, String email,  String tipoUsuario, String pais, String idioma, String rol1, String rol2, String rol3, String rol4, String rol5, String foto, String flagActivo, Long creacionFecha, Long creacionUsuario, Long modificacionFecha, Long modificacionUsuario) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        //this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.pais = pais;
        this.idioma = idioma;
        this.rol1 = rol1;
        this.rol2 = rol2;
        this.rol3 = rol3;
        this.rol4 = rol4;
        this.rol5 = rol5;
        this.foto = foto;
        this.flagActivo = flagActivo;
        this.creacionFecha = creacionFecha;
        this.creacionUsuario = creacionUsuario;
        this.modificacionFecha = modificacionFecha;
        this.modificacionUsuario = modificacionUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getRol1() {
        return rol1;
    }

    public void setRol1(String rol1) {
        this.rol1 = rol1;
    }

    public String getRol2() {
        return rol2;
    }

    public void setRol2(String rol2) {
        this.rol2 = rol2;
    }

    public String getRol3() {
        return rol3;
    }

    public void setRol3(String rol3) {
        this.rol3 = rol3;
    }

    public String getRol4() {
        return rol4;
    }

    public void setRol4(String rol4) {
        this.rol4 = rol4;
    }

    public String getRol5() {
        return rol5;
    }

    public void setRol5(String rol5) {
        this.rol5 = rol5;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFlagActivo() {
        return flagActivo;
    }

    public void setFlagActivo(String flagActivo) {
        this.flagActivo = flagActivo;
    }

    public Long getCreacionFecha() {
        return creacionFecha;
    }

    public void setCreacionFecha(Long creacionFecha) {
        this.creacionFecha = creacionFecha;
    }

    public Long getCreacionUsuario() {
        return creacionUsuario;
    }

    public void setCreacionUsuario(Long creacionUsuario) {
        this.creacionUsuario = creacionUsuario;
    }

    public Long getModificacionFecha() {
        return modificacionFecha;
    }

    public void setModificacionFecha(Long modificacionFecha) {
        this.modificacionFecha = modificacionFecha;
    }

    public Long getModificacionUsuario() {
        return modificacionUsuario;
    }

    public void setModificacionUsuario(Long modificacionUsuario) {
        this.modificacionUsuario = modificacionUsuario;
    }
}
