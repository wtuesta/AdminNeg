package com.adminneg.bussines.administrar.negocio.adminneg.shared_pref;


import android.content.Context;
import android.content.SharedPreferences;

import com.adminneg.bussines.administrar.negocio.adminneg.model.Adm_01usuario;

/**
 * Created by albertopalomarrobledo on 1/12/18.
 */

public class SharedPrefManager {
    private static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    private static final String SHARED_PREFERENCES_ID = "SHARED_PREFERENCES_ID";
    private static final String SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_NAME";
    private static final String SHARED_PREFERENCES_EMAIL = "SHARED_PREFERENCES_EMAIL";
//    private static final String SHARED_PREFERENCES_PASSWORD = "SHARED_PREFERENCES_PASSWORD";
    private static final String SHARED_PREFERENCES_TIPOUSUARIO = "SHARED_PREFERENCES_TIPOUSUARIO";

    private static final String SHARED_PREFERENCES_PAIS = "SHARED_PREFERENCES_PAIS";
    private static final String SHARED_PREFERENCES_IDIOMA = "SHARED_PREFERENCES_IDIOMA";
    private static final String SHARED_PREFERENCES_ROL1 = "SHARED_PREFERENCES_ROL1";
    private static final String SHARED_PREFERENCES_ROL2 = "SHARED_PREFERENCES_ROL2";
    private static final String SHARED_PREFERENCES_ROL3 = "SHARED_PREFERENCES_ROL3";
    private static final String SHARED_PREFERENCES_ROL4 = "SHARED_PREFERENCES_ROL4";
    private static final String SHARED_PREFERENCES_ROL5 = "SHARED_PREFERENCES_ROL5";

    private static final String SHARED_PREFERENCES_FOTO = "SHARED_PREFERENCES_FOTO";
    private static final String SHARED_PREFERENCES_FLAGACTIVO = "SHARED_PREFERENCES_FLAGACTIVO";
    private static final String SHARED_PREFERENCES_FLAGCONFIRMADO = "SHARED_PREFERENCES_FLAGCONFIRMADO";

    private static final String SHARED_PREFERENCES_CREACIONFECHA = "SHARED_PREFERENCES_CREACIONFECHA";
    private static final String SHARED_PREFERENCES_CREACIONUSUARIO = "SHARED_PREFERENCES_CREACIONUSUARIO";
    private static final String SHARED_PREFERENCES_MODIFICACIONFECHA = "SHARED_PREFERENCES_MODIFICACIONFECHA";
    private static final String SHARED_PREFERENCES_MODIFICACIONUSUARIO = "SHARED_PREFERENCES_MODIFICACIONUSUARIO";

    private static SharedPrefManager instance;
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private SharedPrefManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if(instance == null){
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    public void saveProfesor(Adm_01usuario usuario){
        editor.putLong(SHARED_PREFERENCES_ID, usuario.getIdUsuario());
        editor.putString(SHARED_PREFERENCES_NAME, usuario.getNombre());
        editor.putString(SHARED_PREFERENCES_EMAIL, usuario.getEmail());
//        editor.putString(SHARED_PREFERENCES_PASSWORD, usuario.getPassword());
        editor.putString(SHARED_PREFERENCES_TIPOUSUARIO, usuario.getTipoUsuario());
        editor.putString(SHARED_PREFERENCES_PAIS, usuario.getPais());
        editor.putString(SHARED_PREFERENCES_IDIOMA, usuario.getIdioma());
        editor.putString(SHARED_PREFERENCES_ROL1, usuario.getRol1());
        editor.putString(SHARED_PREFERENCES_ROL2, usuario.getRol2());
        editor.putString(SHARED_PREFERENCES_ROL3, usuario.getRol3());
        editor.putString(SHARED_PREFERENCES_ROL4, usuario.getRol4());
        editor.putString(SHARED_PREFERENCES_ROL5, usuario.getRol5());
        editor.putString(SHARED_PREFERENCES_FOTO, usuario.getFoto());

        editor.putString(SHARED_PREFERENCES_FLAGACTIVO, usuario.getFlagActivo());
        editor.putString(SHARED_PREFERENCES_FLAGCONFIRMADO, usuario.getFlagConfirmado());
        editor.putLong(SHARED_PREFERENCES_CREACIONFECHA, usuario.getCreacionFecha());
        editor.putString(SHARED_PREFERENCES_CREACIONUSUARIO, usuario.getCreacionUsuario());
        editor.putLong(SHARED_PREFERENCES_MODIFICACIONFECHA, usuario.getModificacionFecha());
        editor.putString(SHARED_PREFERENCES_MODIFICACIONUSUARIO, usuario.getModificacionUsuario());
        editor.apply();
    }

    public boolean isLoggedIn(){
        if(sharedPreferences.getLong(SHARED_PREFERENCES_ID, -1) != -1){
            return true;
        }
        return false;
    }

    public Adm_01usuario getusuario(){
        Adm_01usuario usuario = new Adm_01usuario(
                sharedPreferences.getLong(SHARED_PREFERENCES_ID, -1),
                sharedPreferences.getString(SHARED_PREFERENCES_NAME, null),
                sharedPreferences.getString(SHARED_PREFERENCES_EMAIL, null),
                sharedPreferences.getString(SHARED_PREFERENCES_TIPOUSUARIO, null),
                sharedPreferences.getString(SHARED_PREFERENCES_PAIS, null),
                sharedPreferences.getString(SHARED_PREFERENCES_IDIOMA, null),
                sharedPreferences.getString(SHARED_PREFERENCES_ROL1, null),
                sharedPreferences.getString(SHARED_PREFERENCES_ROL2, null),
                sharedPreferences.getString(SHARED_PREFERENCES_ROL3, null),
                sharedPreferences.getString(SHARED_PREFERENCES_ROL4, null),
                sharedPreferences.getString(SHARED_PREFERENCES_ROL5, null),
                sharedPreferences.getString(SHARED_PREFERENCES_FOTO, null),
                sharedPreferences.getString(SHARED_PREFERENCES_FLAGACTIVO, null),
                sharedPreferences.getString(SHARED_PREFERENCES_FLAGCONFIRMADO, null),
                sharedPreferences.getLong(SHARED_PREFERENCES_CREACIONFECHA, -1),
                sharedPreferences.getString(SHARED_PREFERENCES_CREACIONUSUARIO, null),
                sharedPreferences.getLong(SHARED_PREFERENCES_MODIFICACIONFECHA, -1),
                sharedPreferences.getString(SHARED_PREFERENCES_MODIFICACIONUSUARIO, null)
        );

        return usuario;
    }

    public void logOut(){
        editor.clear();
        editor.apply();
    }
}
