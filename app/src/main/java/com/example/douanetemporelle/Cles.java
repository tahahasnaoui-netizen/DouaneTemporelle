package com.example.douanetemporelle;


public class Cles {

    // نومرو بطاقة طالب
    public static final String MATRICULE = "00001234";

    // Static method: ياخو اخر 4 نوامر   m matricule as a parameter  اخر 4 نوامر  + "_" + إسم
    public static String construireCle(String matricule, String nomCle) {
        // ياخو اخر 4 نوامر م  matricule
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
