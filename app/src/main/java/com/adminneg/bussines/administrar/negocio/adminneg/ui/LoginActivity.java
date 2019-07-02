package com.adminneg.bussines.administrar.negocio.adminneg.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adminneg.bussines.administrar.negocio.adminneg.R;
import com.adminneg.bussines.administrar.negocio.adminneg.api.WebService;
import com.adminneg.bussines.administrar.negocio.adminneg.api.WebServiceApi;
import com.adminneg.bussines.administrar.negocio.adminneg.apibas.WebServiceJWTBas;
import com.adminneg.bussines.administrar.negocio.adminneg.apibas.WebServiceJWTBasApi;
import com.adminneg.bussines.administrar.negocio.adminneg.model.Adm_01usuario;
import com.adminneg.bussines.administrar.negocio.adminneg.modelws.Param;
import com.adminneg.bussines.administrar.negocio.adminneg.modelws.WSBody;
import com.adminneg.bussines.administrar.negocio.adminneg.modelws.WsAdm_01usuario;
import com.adminneg.bussines.administrar.negocio.adminneg.modelws.WsMaest_04pais_moneda;
import com.adminneg.bussines.administrar.negocio.adminneg.modelws.WsTest;
import com.adminneg.bussines.administrar.negocio.adminneg.shared_pref.SharedPrefManager;
import com.adminneg.bussines.administrar.negocio.adminneg.utilidades.Constantes;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.tvTitulo)
    TextView tvTitulo;
    @BindView(R.id.imgLogo)
    ImageView imgLogo;
    private Adm_01usuario usuario;
    private WSBody body;
    private WsTest test;
    private String token;
    private Param param;

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btLogin)
    Button btLogin;
    @BindView(R.id.tvSignUp)
    TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ViewCompat.setTransitionName(imgLogo,Constantes.SHARED_VIEW_PHOTO);
        ViewCompat.setTransitionName(tvTitulo,Constantes.SHARED_VIEW_TITLE);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });


        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), SignUpActivity.class));

                /*Intent i = new Intent(getApplicationContext(), SignUpActivity.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(imgLogo, "imageTransition");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);

                startActivity(i, options.toBundle());*/

                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                Pair[] pairs = new Pair[2];
                pairs[0]= new Pair<View,String>(imgLogo,Constantes.SHARED_VIEW_PHOTO);
                pairs[1]= new Pair<View,String>(tvTitulo,Constantes.SHARED_VIEW_TITLE);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                startActivity(i, options.toBundle());
            }
        });
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    input.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    /*RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                    lp.setMargins(0, 138, 0, 0);
                    tvAdmineg.setLayoutParams(lp);*/
                }
            }
        });
        btLogin.setFocusableInTouchMode(true);
        btLogin.requestFocus();
        wsObtenerPais();
        //wsObtenerToken();
    }

    private void userLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError(getResources().getString(R.string.email_error));
            etEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError(getResources().getString(R.string.email_doesnt_match));
            etEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            etPassword.setError(getResources().getString(R.string.password_error));
            etPassword.requestFocus();
            return;
        }

        if (password.length() < 4) {
            etPassword.setError(getResources().getString(R.string.password_error_less_than));
            etPassword.requestFocus();
            return;
        }

        usuario = new Adm_01usuario();
        usuario.setEmail(email);
        usuario.setPassword(password);
        login();

    }

    private void login() {
        Call<List<WsAdm_01usuario>> call = WebService
                .getInstance()
                .createService(WebServiceApi.class)
                .login2(usuario)
                ;

        call.enqueue(new Callback<List<WsAdm_01usuario>>() {
            @Override
            public void onResponse(Call<List<WsAdm_01usuario>> call, Response<List<WsAdm_01usuario>> response) {
                if (response.code() == 200) {
                    Log.d("TAG1", "Usuario logeado " + " id " + response.body().get(0).getErr()
                            + " NroError: " + response.body().get(0).getNroError()
                    );

                    usuario = response.body().get(0).getRegistro().get(0);
                    SharedPrefManager.getInstance(getApplicationContext())
                            .saveUsuario(usuario);

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else if (response.code() == 400) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.errLogueo), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 203) {
                    Toast.makeText(getApplicationContext(), R.string.email_error_no_confirm, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<WsAdm_01usuario>> call, Throwable t) {

            }
        });


       /* call.enqueue(new Callback<List<Adm_01usuario>>() {
            @Override
            public void onResponse(Call<List<Adm_01usuario>> call, Response<List<Adm_01usuario>> response) {
                if(response.code() == 200){
                    Log.d("TAG1", "Usuario logeado " + " id " + response.body().get(0).getIdUsuario()
                            + " email: " + response.body().get(0).getEmail());
                    SharedPrefManager.getInstance(getApplicationContext())
                            .saveProfesor(response.body().get(0));
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }else if (response.code()==400){
                    Log.d("TAG1", "Usuario no existe");
                }else{
                    Log.d("TAG1", "Usuario Desconocido");
                }
            }

            @Override
            public void onFailure(Call<List<Adm_01usuario>> call, Throwable t) {

            }
        });
*/
    }
    private void wsObtenerPais() {
       // String a[]={""};
        param = new Param();
        body = new WSBody();
        body.setName("getPaisMonedaTodos");
        body.setParam(param);

        //String parametro="{\"name\":\"getPaisMonedaTodos\",\"param\":{}}";
        String authHeader = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NjIwMjgwNDEsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MzExNzIyODA0MSwidXNlcklkIjoidXNlckBhZG1pbm5lZy5jb20ifQ.7r31CwSaKjB2-OhyrtBz-C-1_tHfSeZqn5kDb0zZhSw";
        Call<List<WsMaest_04pais_moneda>> call = WebServiceJWTBas
                .getInstance()
                .createService(WebServiceJWTBasApi.class)
                .obtenerPaises(authHeader, body);

        call.enqueue(new Callback<List<WsMaest_04pais_moneda>>() {
            @Override
            public void onResponse(Call<List<WsMaest_04pais_moneda>> call, Response<List<WsMaest_04pais_moneda>> response) {
                if (response.code() == 200) {
                    for (int i = 0; i < response.body().size(); i++) {
                        Log.d("TAG1", "Movimiento Bancario: " + i + " " + response.body().get(i));
                    }
                } else {
                    Log.d("TAG1", "Token es incorrecto y no podemos obtener la respuesta");
                }
            }

            @Override
            public void onFailure(Call<List<WsMaest_04pais_moneda>> call, Throwable t) {

            }
        });
    }
    private void wsObtenerToken() {
            test = new WsTest();
            test.setUser("alberto");
            test.setPassword("1234");

                Call<List<String>> call = WebServiceJWTBas
                        .getInstance()
                        .createService(WebServiceJWTBasApi.class)
                        .obtenerToken(test); //metodo de WebServiceApi

                call.enqueue(new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                        if(response.code()==200){
                            token = response.body().get(0).toString();
                            Log.d("TAG1", "El token es: " + token);
                        }else if(response.code()==401){
                            Log.d("TAG1", "No Autorizado");
                        }else{
                            Log.d("TAG1", "No obtenido token");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<String>> call, Throwable t) {

                    }
                });

            }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        int salir = prefe.getInt("salir", 0);
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Log.d("TAG1", "usuario ya esta logeado, enviando a Profile Activity");
            if (salir == 0) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            } else {
                finish();
            }
        } else {
            if (salir == 1) {
                finish();
            }
        }
    }
}
