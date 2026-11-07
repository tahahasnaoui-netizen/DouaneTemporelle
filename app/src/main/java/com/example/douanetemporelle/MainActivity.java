package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editNom, editNaissance, editDestination;
    Spinner spinnerMotif;
    Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1) Link the XML views with the Java code
        editNom = findViewById(R.id.edit_nom_nj);
        editNaissance = findViewById(R.id.edit_naissance_nj);
        editDestination = findViewById(R.id.edit_destination_nj);
        spinnerMotif = findViewById(R.id.spinner_motif_nj);
        btnInspecter = findViewById(R.id.btn_inspecter_nj);

        // 2) Fill the Spinner with the three travel reasons
        String[] motifs = {"Tourisme", "Modification du passé", "Fuite"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, motifs);
        spinnerMotif.setAdapter(adapter);

        // 3) When the "Inspecter le profil" button is clicked
        btnInspecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 4) Read the data entered by the user
                String nom = editNom.getText().toString().trim();
                String sNaissance = editNaissance.getText().toString().trim();
                String sDestination = editDestination.getText().toString().trim();
                String motif = spinnerMotif.getSelectedItem().toString();

                // 5) Check that the fields are not empty
                if (nom.isEmpty() || sNaissance.isEmpty() || sDestination.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 6) Convert the years from text to integers
                int naissance = Integer.parseInt(sNaissance);
                int destination = Integer.parseInt(sDestination);

                // 7) Check that the destination year is greater than the birth year
                if (destination <= naissance) {
                    Toast.makeText(MainActivity.this,
                            "L'année de destination doit être supérieure à l'année de naissance",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                // 8) Compute the temporal age = destination year - birth year
                int ageTemporel = destination - naissance;

                // 9) Create an explicit Intent to go to VerificationActivity
                Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

                // 10) Send the data using keys built by a static method (not hardcoded)
                intent.putExtra(Cles.cleNom(Cles.MATRICULE), nom);
                intent.putExtra(Cles.cleNaissance(Cles.MATRICULE), naissance);
                intent.putExtra(Cles.cleDestination(Cles.MATRICULE), destination);
                intent.putExtra(Cles.cleMotif(Cles.MATRICULE), motif);
                intent.putExtra(Cles.cleAge(Cles.MATRICULE), ageTemporel);

                startActivity(intent);
            }
        });
    }
}
