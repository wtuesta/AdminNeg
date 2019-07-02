package com.adminneg.bussines.administrar.negocio.adminneg.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adminneg.bussines.administrar.negocio.adminneg.R;
import com.adminneg.bussines.administrar.negocio.adminneg.model.Adm_01usuario;
import com.adminneg.bussines.administrar.negocio.adminneg.utilidades.Constantes;
import com.adminneg.bussines.administrar.negocio.adminneg.utilidades.Utilidades;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {
    @BindView(R.id.imgLogo)
    ImageView imgLogo;
    @BindView(R.id.tvTitulo)
    TextView tvTitulo;
    private Adm_01usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        //conexexion de los elementos de animacion
        ViewCompat.setTransitionName(imgLogo,Constantes.SHARED_VIEW_PHOTO);
        ViewCompat.setTransitionName(tvTitulo,Constantes.SHARED_VIEW_TITLE);
    }


    @OnClick({R.id.btAnterior, R.id.btSiguiente})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btAnterior:
                finish();
                break;
            case R.id.btSiguiente:
                /*ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
                Intent i = new Intent(getApplicationContext(), SignUp2Activity.class);
                startActivity(i, options.toBundle());
                */

                /*Intent i = new Intent(getApplicationContext(), SignUp2Activity.class);
                Pair[] pairs = new Pair[1];
                pairs[0]= new Pair<View,String>(imgLogo,"imageTransition");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
                startActivity(i, options.toBundle());
                */

                Intent i = new Intent(getApplicationContext(), SignUp2Activity.class);
                Pair[] pairs = new Pair[2];
                pairs[0]= new Pair<View,String>(imgLogo,Constantes.SHARED_VIEW_PHOTO);
                pairs[1]= new Pair<View,String>(tvTitulo,Constantes.SHARED_VIEW_TITLE);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
                startActivity(i, options.toBundle());

                break;
        }
    }
}
