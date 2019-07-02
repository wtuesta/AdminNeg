package com.adminneg.bussines.administrar.negocio.adminneg.ui;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adminneg.bussines.administrar.negocio.adminneg.R;
import com.adminneg.bussines.administrar.negocio.adminneg.utilidades.Constantes;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUp2Activity extends AppCompatActivity {

    @BindView(R.id.imgLogo)
    ImageView imgLogo;
    @BindView(R.id.tvTitulo)
    TextView tvTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
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
                finish();
                break;
        }
    }
}
