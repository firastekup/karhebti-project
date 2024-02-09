package com.example.karhebti;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class Page2Activity extends AppCompatActivity {

    private Spinner modeleSpinner; // Déclarer le Spinner modeleSpinner au niveau de la classe

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

        Spinner spinner = findViewById(R.id.spinner_marque);
        modeleSpinner = findViewById(R.id.spinner_modele); // Initialiser le Spinner modeleSpinner

        String[] carBrands = getResources().getStringArray(R.array.car_brands);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carBrands);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedBrand = (String) parent.getItemAtPosition(position);
                loadModelsForBrand(selectedBrand); // Appeler la méthode loadModelsForBrand ici
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ne rien faire si aucune marque n'est sélectionnée
            }
        });
    }

    // Déplacer la méthode loadModelsForBrand à l'extérieur de la méthode onCreate
    private void loadModelsForBrand(String brand) {
        int modelsResId = getResources().getIdentifier(brand.toLowerCase() + "_models", "array", getPackageName());
        if (modelsResId != 0) {
            String[] models = getResources().getStringArray(modelsResId);
            ArrayAdapter<String> modeleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, models);
            modeleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            modeleSpinner.setAdapter(modeleAdapter);
        } else {
            modeleSpinner.setAdapter(null);
        }
    }
}
