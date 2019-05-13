package com.adminneg.bussines.administrar.negocio.adminneg.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.adminneg.bussines.administrar.negocio.adminneg.R;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.AgendaFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.ClienteFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.CompraFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.ConfiguracionFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.ContactarFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.CopiaSeguridadFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.HomeAdminFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.HomeUserFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.IngrGastoFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.NegocioSucursalFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.ProdServFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.ProveedorFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.ReporteFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.TerminosCondFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.TrabajadoresFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.VentaFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.model.Adm_01usuario;
import com.adminneg.bussines.administrar.negocio.adminneg.shared_pref.SharedPrefManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, VentaFragment.OnFragmentInteractionListener, CompraFragment.OnFragmentInteractionListener, ProdServFragment.OnFragmentInteractionListener,
        ClienteFragment.OnFragmentInteractionListener, ProveedorFragment.OnFragmentInteractionListener, IngrGastoFragment.OnFragmentInteractionListener, AgendaFragment.OnFragmentInteractionListener, NegocioSucursalFragment.OnFragmentInteractionListener,
        TrabajadoresFragment.OnFragmentInteractionListener, ReporteFragment.OnFragmentInteractionListener, ConfiguracionFragment.OnFragmentInteractionListener, CopiaSeguridadFragment.OnFragmentInteractionListener,
        ContactarFragment.OnFragmentInteractionListener, TerminosCondFragment.OnFragmentInteractionListener, HomeUserFragment.OnFragmentInteractionListener, HomeAdminFragment.OnFragmentInteractionListener {
    private SharedPreferences.Editor editor;
    private Adm_01usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        usuario= SharedPrefManager.getInstance(getApplicationContext())
                .getusuario();
        muestraFragmentoInicial();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void muestraFragmentoInicial() {
        String tipoUsuario = usuario.getTipoUsuario();
        Fragment miFragment=null;
        if(tipoUsuario.equals("P")){
            miFragment = new HomeAdminFragment();
        }
        else{
            miFragment = new HomeUserFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            guadarMarcaSalir();
            super.onBackPressed();
        }
    }

    private void guadarMarcaSalir() {
        SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putInt("salir", 1);
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment miFragment=null;
        boolean fragmentSeleccionado=false;

        if (id == R.id.nav_ventas) {
            miFragment = new VentaFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_compras) {
            miFragment = new CompraFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_producto) {
            miFragment = new ProdServFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_clientes) {
            miFragment = new ClienteFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_proveedores) {
            miFragment = new ProveedorFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_ingresos_egresos) {
            miFragment = new IngrGastoFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_agenda) {
            miFragment = new AgendaFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_negocios) {
            miFragment = new NegocioSucursalFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_personal) {
            miFragment = new TrabajadoresFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_reportes) {
            miFragment = new ReporteFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_configuracion) {
            miFragment = new ConfiguracionFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_miperfil) {
            Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
            //intent.putExtra(Artista.ORDEN, adapter.getItemCount()+1);
            startActivity(intent);
            //startActivityForResult(intent, 1);

            /*miFragment = new MiPerfilFragment();
            fragmentSeleccionado=true;*/
        } else if (id == R.id.nav_backup) {
            miFragment = new CopiaSeguridadFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_salir) {
            guadarMarcaSalir();
            finish();
        }
        else if (id == R.id.nav_cerrar_sesion) {
            guadarMarcaSalir();
            SharedPrefManager.getInstance(getApplicationContext()).logOut();
            finish();
        }
        if(fragmentSeleccionado){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;*/
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
