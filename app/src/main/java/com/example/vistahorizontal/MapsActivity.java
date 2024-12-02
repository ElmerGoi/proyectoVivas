package com.example.vistahorizontal;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.vistahorizontal.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    // Declaración de EditText
    private EditText txtLatitud;
    private EditText txtLongitud;

    // Variable global para mantener la referencia al GoogleMap y el marcador
    private GoogleMap mMap;
    private Marker currentMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2); // Cambiado a activity2.xml

        // Inicialización de las variables EditText
        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);

        // Obtener el fragmento del mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        // Registrar el callback para obtener el mapa cuando esté listo
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Asignamos el mapa a la variable global
        mMap = googleMap;

        // Crear la instancia LatLng correctamente en Java
        LatLng mexico = new LatLng(19.4326, -99.1332);

        // Agregar un marcador inicial en la ubicación de México
        currentMarker = mMap.addMarker(new MarkerOptions().position(mexico).title("México"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mexico, 10f));

        // Configurar el listener para hacer algo cuando se haga clic en el mapa
        mMap.setOnMapClickListener(latLng -> {
            // Actualizar el marcador con las nuevas coordenadas
            if (currentMarker == null) {
                currentMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("Nuevo marcador"));
            } else {
                currentMarker.setPosition(latLng);
            }
            currentMarker.setTitle("Nuevo marcador");
            currentMarker.showInfoWindow();

            // Mostrar las coordenadas en los EditText
            txtLatitud.setText(String.valueOf(latLng.latitude));
            txtLongitud.setText(String.valueOf(latLng.longitude));
        });
    }
}
