package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    TextView txtNom, txtAge, txtMotif, txtNaissance, txtDestination, txtAlerte;
    Button btnApprouver, btnRefuser;
    String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // yorbet  xml b java code
        txtNom = findViewById(R.id.txt_nom_nj);
        txtAge = findViewById(R.id.txt_age_nj);
        txtMotif = findViewById(R.id.txt_motif_nj);
        txtNaissance = findViewById(R.id.txt_naissance_nj);
        txtDestination = findViewById(R.id.txt_destination_nj);
        txtAlerte = findViewById(R.id.txt_alerte_nj);
        btnApprouver = findViewById(R.id.btn_approuver_nj);
        btnRefuser = findViewById(R.id.btn_refuser_nj);

        // yekhou data li tba3thet ml MainActivity
        Intent intent = getIntent();
        nom = intent.getStringExtra(Cles.cleNom(Cles.MATRICULE));
        int naissance = intent.getIntExtra(Cles.cleNaissance(Cles.MATRICULE), 0);
        int destination = intent.getIntExtra(Cles.cleDestination(Cles.MATRICULE), 0);
        String motif = intent.getStringExtra(Cles.cleMotif(Cles.MATRICULE));
        int ageTemporel = intent.getIntExtra(Cles.cleAge(Cles.MATRICULE), 0);

        // ywari l data mta3 l voyageur
        txtNom.setText("Nom : " + nom);
        txtAge.setText("Âge temporel : " + ageTemporel + " ans");
        txtMotif.setText("Motif : " + motif);
        txtNaissance.setText("Année de naissance : " + naissance);
        txtDestination.setText("Année de destination : " + destination);

        // كان الفرق عدد أولي ينحي button approve
        if (motif.equals("Modification du passé") && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
            txtAlerte.setText("Risque de paradoxe détecté !");
        }


        btnApprouver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lancerResultat(true);
            }
        });

        btnRefuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lancerResultat(false);
            }
        });
    }

    // ythabet nombre premier wale
    public boolean estPremier(int n) {
        if (n < 2) {
            return false;
        }
        // ye9sem l 3dad m 2 l n-1
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // tab3ath l verdict boleen w m3ana l esm l ResultActivity
    public void lancerResultat(boolean verdict) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Cles.cleVerdict(Cles.MATRICULE), verdict);
        intent.putExtra(Cles.cleNom(Cles.MATRICULE), nom);
        startActivity(intent);
    }
}
