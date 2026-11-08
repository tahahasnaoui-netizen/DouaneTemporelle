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

        // yorbet  xml b java code
        editNom = findViewById(R.id.edit_nom_nj);
        editNaissance = findViewById(R.id.edit_naissance_nj);
        editDestination = findViewById(R.id.edit_destination_nj);
        spinnerMotif = findViewById(R.id.spinner_motif_nj);
        btnInspecter = findViewById(R.id.btn_inspecter_nj);

        // y3abi spinner b asbeb mtaa l safra
        String[] motifs = {"Tourisme", "Modification du passé", "Fuite"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, motifs);
        spinnerMotif.setAdapter(adapter);

        // wa9t nenzlou 3la "Inspecter le profil"
        btnInspecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ya9ra el data li ktebha l utilisateur
                String nom = editNom.getText().toString().trim();
                String sNaissance = editNaissance.getText().toString().trim();
                String sDestination = editDestination.getText().toString().trim();
                String motif = spinnerMotif.getSelectedItem().toString();

                // ychoufha fergha wale
                if (nom.isEmpty() || sNaissance.isEmpty() || sDestination.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                // ybadel l 3am m text l integer
                int naissance = Integer.parseInt(sNaissance);
                int destination = Integer.parseInt(sDestination);

                // ythabet date destination akber m date naissance
                if (destination <= naissance) {
                    Toast.makeText(MainActivity.this,
                            "L'année de destination doit être supérieure à l'année de naissance",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                // ye7seb temporal age
                int ageTemporel = destination - naissance;


                Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

                // yab3ath e data
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
