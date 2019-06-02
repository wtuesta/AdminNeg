package com.adminneg.bussines.administrar.negocio.adminneg.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.adminneg.bussines.administrar.negocio.adminneg.R;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.HomeAdminFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.fragments.HomeUserFragment;
import com.adminneg.bussines.administrar.negocio.adminneg.model.Adm_01usuario;
import com.adminneg.bussines.administrar.negocio.adminneg.shared_pref.SharedPrefManager;

import com.adminneg.bussines.administrar.negocio.adminneg.model.ChildModel;
import com.adminneg.bussines.administrar.negocio.adminneg.model.HeaderModel;

import com.adminneg.bussines.administrar.negocio.adminneg.utilidades.Common;
import com.adminneg.bussines.administrar.negocio.adminneg.view.ExpandableNavigationListView;


public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, HomeUserFragment.OnFragmentInteractionListener, HomeAdminFragment.OnFragmentInteractionListener {
    private SharedPreferences.Editor editor;
    private Adm_01usuario usuario;

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private ExpandableNavigationListView navigationExpandableListView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context=MainActivity.this;
        //Generando Menu
        navigationExpandableListView = (ExpandableNavigationListView) findViewById(R.id.expandable_navigation);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationExpandableListView
                .init(this)
                .addHeaderModel(new HeaderModel(getResources().getString(R.string.inicio)))  //0
                .addHeaderModel(new HeaderModel(getResources().getString(R.string.ventas), R.drawable.cartbackground, false, true, false)) //1
                .addHeaderModel(new HeaderModel(getResources().getString(R.string.compras))) //2
                .addHeaderModel(new HeaderModel(getResources().getString(R.string.producto_servicio))) //3
                .addHeaderModel(new HeaderModel(getResources().getString(R.string.clientes)))  //4
                .addHeaderModel(new HeaderModel(getResources().getString(R.string.proveedores))) //5

                .addHeaderModel(
                        new HeaderModel(getResources().getString(R.string.ajustes), R.drawable.abajo, true)
                                .addChildModel(new ChildModel("    " + getResources().getString(R.string.ajustes_inventario)))  //6-0
                                .addChildModel(new ChildModel("    " + getResources().getString(R.string.ingresos_gastos)))     //6-1
                )

                .addHeaderModel(
                        new HeaderModel(getResources().getString(R.string.configuracion), R.drawable.abajo, true)
                                .addChildModel(new ChildModel("    " + getResources().getString(R.string.mi_perfil)))           //7-0
                                .addChildModel(new ChildModel("    " + getResources().getString(R.string.negocios_sucursales)))  //7-1
                                .addChildModel(new ChildModel("    " + getResources().getString(R.string.administrar_personal))) //7-2
                                .addChildModel(new ChildModel("    " + getResources().getString(R.string.configuracion_general))) //7-3
                )
                .addHeaderModel(
                        new HeaderModel(getResources().getString(R.string.herramientas), R.drawable.abajo, true)
                                .addChildModel(new ChildModel("    " + getResources().getString(R.string.recargar)))            //8-0
                                .addChildModel(new ChildModel("    " + getResources().getString(R.string.copia_seguridad)))     //8-1
                                .addChildModel(new ChildModel("    " + getResources().getString(R.string.rastreo)))             //8-2
                                .addChildModel(new ChildModel("    " + getResources().getString(R.string.cerrar_sesion)))       //8-3
                )

                .addHeaderModel(new HeaderModel(getResources().getString(R.string.salir)))   //9
                .build()
                .addOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        navigationExpandableListView.setSelected(groupPosition);

                        //drawer.closeDrawer(GravityCompat.START);
                        if (id == 0) {
                            //Home Menu
                            Common.showToast(context, "Inicio Select " );

                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 1) {
                            //Cart Menu
                            Common.showToast(context, "Ventas Select");
                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 2) {
                            //Categories Menu
                            Common.showToast(context, "Compras  Select");
                        } else if (id == 3) {
                            //Orders Menu
                            Common.showToast(context, "Producto y servicio");
                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 4) {
                            //Wishlist Menu
                            Common.showToast(context, "Clientes Selected");
                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 5) {
                            //Notifications Menu
                            Common.showToast(context, "Proveedores");
                            drawer.closeDrawer(GravityCompat.START);
                        }else if (id == 9) {
                            //Salir
                            guadarMarcaSalir();
                            finish();
                            drawer.closeDrawer(GravityCompat.START);
                        }
                        return false;
                    }
                })
                .addOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        navigationExpandableListView.setSelected(groupPosition, childPosition);

                        if (id == 0 && groupPosition==6) {
                            Common.showToast(context, "Ajustes de Inventario" + String.valueOf(groupPosition) + " / " + String.valueOf(childPosition));
                        } else if (id == 1 && groupPosition==6) {
                            Common.showToast(context, "Ingresos y Gastos"+ String.valueOf(groupPosition) + " / " + String.valueOf(childPosition));
                        } else if (id == 0 && groupPosition==7) {
                            Common.showToast(context, "Mi Perfil"+ String.valueOf(groupPosition) + " / " + String.valueOf(childPosition));
                        } else if (id == 1 && groupPosition==7) {
                            Common.showToast(context, "Negocios y Sucursales"+ String.valueOf(groupPosition) + " / " + String.valueOf(childPosition));
                        } else if (id == 2 && groupPosition==7) {
                            Common.showToast(context, "Personal"+ String.valueOf(groupPosition) + " / " + String.valueOf(childPosition));
                        } else if (id == 3 && groupPosition==7) {
                            Common.showToast(context, "Configurar App"+ String.valueOf(groupPosition) + " / " + String.valueOf(childPosition));
                        }else if (id == 0 && groupPosition==8) {
                            Common.showToast(context, "Recargar"+ String.valueOf(groupPosition) + " / " + String.valueOf(childPosition));
                        }else if (id == 1 && groupPosition==8) {
                            Common.showToast(context, "Copia de Seguridad"+ String.valueOf(groupPosition) + " / " + String.valueOf(childPosition));
                        }else if (id == 2 && groupPosition==8) {
                            Common.showToast(context, "Rastreo"+ String.valueOf(groupPosition) + " / " + String.valueOf(childPosition));
                        }else if (id == 3 && groupPosition==8) {
                            //Cerrar sesion
                            guadarMarcaSalir();
                            SharedPrefManager.getInstance(getApplicationContext()).logOut();
                            finish();
                            //Common.showToast(context, "Cerrar Sesion"+ String.valueOf(groupPosition) + " / " + String.valueOf(childPosition));
                        }

                        drawer.closeDrawer(GravityCompat.START);
                        return false;
                    }
                });
        navigationExpandableListView.expandGroup(2);

        //Ubicando el fragmento inicial
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
        
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        /*NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/
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
        //    miFragment = new VentaFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_compras) {
        //    miFragment = new CompraFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_producto) {
         //   miFragment = new ProdServFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_clientes) {
         //   miFragment = new ClienteFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_proveedores) {
         //   miFragment = new ProveedorFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_ingresos_egresos) {
          //  miFragment = new IngrGastoFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_agenda) {
          //  miFragment = new AgendaFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_negocios) {

            //action_buscar
            Intent i = new Intent(this, SucursalListadoActivity.class );
            startActivityForResult(i, 1);
        } else if (id == R.id.nav_personal) {
          //  miFragment = new TrabajadoresFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_reportes) {
          //  miFragment = new ReporteFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_configuracion) {
           // miFragment = new ConfiguracionFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_miperfil) {
            /*Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
            startActivity(intent);*/
        } else if (id == R.id.nav_backup) {
            //miFragment = new CopiaSeguridadFragment();
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
        /*if(fragmentSeleccionado){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        return true;

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
