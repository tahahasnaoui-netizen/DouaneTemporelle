package com.example.douanetemporelle;


public class Cles {

    // num matricule
    public static final String MATRICULE = "3012";

    // yekhou l matricule parametre
    public static String construireCle(String matricule, String nomCle) {
        // yekhou ekher 4nwemer m matricule
        String quatreChiffres = matricule.substring(matricule.length() - 4);
        return quatreChiffres + "_" + nomCle;
    }

    public static String cleNom(String matricule) {
        return construireCle(matricule, "NOM_VOYAGEUR");
    }

    public static String cleNaissance(String matricule) {
        return construireCle(matricule, "ANNEE_NAISSANCE");
    }

    public static String cleDestination(String matricule) {
        return construireCle(matricule, "ANNEE_DESTINATION");
    }

    public static String cleMotif(String matricule) {
        return construireCle(matricule, "MOTIF");
    }

    public static String cleAge(String matricule) {
        return construireCle(matricule, "AGE_TEMPOREL");
    }

    public static String cleVerdict(String matricule) {
        return construireCle(matricule, "VERDICT");
    }
}
