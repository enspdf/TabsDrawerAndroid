package com.shackox.me.restauranterico.Ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.shackox.me.restauranterico.R;

public class ActividadPrincipal extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        agregarToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            prepararDrawer(navigationView);
            seleccionarItem(navigationView.getMenu().getItem(0));
        }
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                seleccionarItem(menuItem);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()) {
            case R.id.item_inicio:
                fragmentoGenerico = new FragmentoInicio();
                break;
            case R.id.item_parqueadero:
                fragmentoGenerico = new FragmentoCuenta();
                break;
            case R.id.item_bahia:
                fragmentoGenerico = new FragmentoCategorias();
                break;
            case R.id.item_tipo_vehiculo:
                //fragmentoGenerico = new FragmentosTipoVehiculo();
                break;
            case R.id.item_tarifa:
                //fragmentoGenerico = new FragmentoTarifa();
                break;
            case R.id.item_configuracion:
                startActivity(new Intent(this, ActividadConfiguracion.class));
                break;
        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }
        setTitle(itemDrawer.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
