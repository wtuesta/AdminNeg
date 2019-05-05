package com.adminneg.bussines.administrar.negocio.adminneg.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adminneg.bussines.administrar.negocio.adminneg.R;
import com.adminneg.bussines.administrar.negocio.adminneg.api.WebService;
import com.adminneg.bussines.administrar.negocio.adminneg.api.WebServiceApi;
import com.adminneg.bussines.administrar.negocio.adminneg.model.Adm_01usuario;
import com.adminneg.bussines.administrar.negocio.adminneg.modelws.WsAdm_01usuario;
import com.adminneg.bussines.administrar.negocio.adminneg.shared_pref.SharedPrefManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Adm_01usuario usuario;
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

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });
    }
    private void userLogin(){
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(email.isEmpty()){
            etEmail.setError(getResources().getString(R.string.email_error));
            etEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError(getResources().getString(R.string.email_doesnt_match));
            etEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            etPassword.setError(getResources().getString(R.string.password_error));
            etPassword.requestFocus();
            return;
        }

        if(password.length()<4){
            etPassword.setError(getResources().getString(R.string.password_error_less_than));
            etPassword.requestFocus();
            return;
        }

        usuario = new Adm_01usuario();
        usuario.setEmail(email);
        usuario.setPassword(password);
        login();
    }

    private void login(){
        Call<List<WsAdm_01usuario>> call = WebService
                .getInstance()
                .createService(WebServiceApi.class)
                .login2(usuario);

        call.enqueue(new Callback<List<WsAdm_01usuario>>() {
            @Override
            public void onResponse(Call<List<WsAdm_01usuario>> call, Response<List<WsAdm_01usuario>> response) {
                if(response.code() == 200) {
                    Log.d("TAG1", "Usuario logeado " + " id " + response.body().get(0).getErr()
                            + " NroError: " + response.body().get(0).getNroError()
                    );

                    usuario = response.body().get(0).getRegistro().get(0);
                    SharedPrefManager.getInstance(getApplicationContext())
                            .saveProfesor(usuario);

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else if (response.code()==400){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.errLogueo),Toast.LENGTH_SHORT).show();
                }else if(response.code()==203){
                    Toast.makeText(getApplicationContext(),R.string.email_error_no_confirm,Toast.LENGTH_LONG).show();
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

    @Override
    protected void onStart(){
        super.onStart();
        SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
        int salir = prefe.getInt("salir",0);
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Log.d("TAG1", "usuario ya esta logeado, enviando a Profile Activity");
            if (salir==0) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
            else
            {
                finish();
            }
        }
        else
        {
            if (salir==1) {
                finish();
            }
        }
    }
}
