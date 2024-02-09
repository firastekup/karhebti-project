package com.example.karhebti;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class Page2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

        // Récupérer les Spinners
        Spinner spinner_marque = findViewById(R.id.spinner_marque);
        Spinner spinner_modele = findViewById(R.id.spinner_modele);

        // Récupérer les marques de voiture depuis les ressources
        String[] carBrands = getResources().getStringArray(R.array.car_brands);

        // Créer un ArrayAdapter pour les marques de voiture
        ArrayAdapter<String> adapterMarques = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carBrands);
        adapterMarques.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Lier l'ArrayAdapter au Spinner des marques
        spinner_marque.setAdapter(adapterMarques);

        // Définir un écouteur d'événements sur le Spinner des marques
        spinner_marque.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Récupérer la marque sélectionnée
                String selectedBrand = parent.getItemAtPosition(position).toString();

                // Charger les modèles correspondant à la marque sélectionnée
                int modelsResourceId = getResources().getIdentifier(selectedBrand.toLowerCase() + "_models", "array", getPackageName());
                String[] carModels = getResources().getStringArray(modelsResourceId);

                // Créer un ArrayAdapter pour les modèles de voiture
                ArrayAdapter<String> adapterModeles = new ArrayAdapter<>(Page2Activity.this, android.R.layout.simple_spinner_item, carModels);
                adapterModeles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Lier l'ArrayAdapter au Spinner des modèles
                spinner_modele.setAdapter(adapterModeles);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ne rien faire si aucune marque n'est sélectionnée
            }
        });
    }
}
