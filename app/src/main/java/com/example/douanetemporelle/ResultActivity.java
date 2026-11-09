package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    LinearLayout layoutResultat;
    TextView txtMessage;
    Button btnNouveau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // yorbet   xml b java code
        layoutResultat = findViewById(R.id.layout_resultat_nj);
        txtMessage = findViewById(R.id.txt_message_nj);
        btnNouveau = findViewById(R.id.btn_nouveau_nj);

        // yekhou verdict w nom
        Intent intent = getIntent();
        boolean verdict = intent.getBooleanExtra(Cles.cleVerdict(Cles.MATRICULE), false);
        String nom = intent.getStringExtra(Cles.cleNom(Cles.MATRICULE));

        // ybadel loun background w message
        if (verdict) {
            // acceptée => green
            layoutResultat.setBackgroundColor(Color.parseColor("#2E7D32"));
            txtMessage.setText("Bon voyage " + nom);
        } else {
            // refusée => red
            layoutResultat.setBackgroundColor(Color.parseColor("#C62828"));
            txtMessage.setText(nom + " est en état d'arrestation temporelle");
        }

        //  button voyageur jdid
        btnNouveau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retour = new Intent(ResultActivity.this, MainActivity.class);
                // FLAG_ACTIVITY_CLEAR_TOP closes all the activities above MainActivity
                retour.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(retour);
                finish();
            }
        });
    }
}
