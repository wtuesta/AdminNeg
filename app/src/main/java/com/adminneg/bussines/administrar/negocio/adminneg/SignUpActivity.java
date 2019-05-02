package com.adminneg.bussines.administrar.negocio.adminneg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adminneg.bussines.administrar.negocio.adminneg.api.WebService;
import com.adminneg.bussines.administrar.negocio.adminneg.api.WebServiceApi;
import com.adminneg.bussines.administrar.negocio.adminneg.model.Adm_01usuario;
import com.adminneg.bussines.administrar.negocio.adminneg.shared_pref.SharedPrefManager;
import com.adminneg.bussines.administrar.negocio.adminneg.utilidades.Utilidades;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private Adm_01usuario usuario;

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btSignUp)
    Button btSignUp;
    @BindView(R.id.tvLogin)
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignUp();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    private void userSignUp(){
        String email = etEmail.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(name.isEmpty()){
            etName.setError(getResources().getString(R.string.name_error));
            etName.requestFocus();
            return;
        }

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
        usuario.setNombre(name);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setTipoUsuario("P"); //Todo el que crea por defecto es propietario
        usuario.setRol1("1");
        usuario.setRol2("1");
        usuario.setRol3("1");
        usuario.setRol4("1");
        usuario.setRol5("1");
        usuario.setCreacionFecha(Utilidades.fechaHoraActual);
        usuario.setModificacionFecha(Utilidades.fechaHoraActual);
        crearUsuario();
    }

    private void crearUsuario(){
        Call<Void> call = WebService
                .getInstance()
                .createService(WebServiceApi.class)
                .registrarUsuario(usuario);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200){
                    Log.d("TAG1", "usuario guardado correctamente");
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }else if(response.code()==409){
                    Toast.makeText(getApplicationContext(),R.string.email_error_no_update,Toast.LENGTH_SHORT).show();
                    Log.d("TAG1", "usuario ya existe");
                }else{
                    Log.d("TAG1", "error no definido");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("TAG1 Error: ", t.getMessage().toString());
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Log.d("TAG1", "Profesor ya esta logeado, enviando a Profile Activity");
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}
