package com.example.QueryBuilderExample;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "recettes")
public class Recette {

    public static final String COLONNE_NOM = "nom";
    public static final String COLONNE_DIFFICULTE = "difficulte";
    public static final String COLONNE_HEURE_PREP = "heure_prep";
    public static final String COLONNE_MINUTE_PREP = "min_prep";
    public static final String COLONNE_HEURE_CUISSON = "heure_cuisson";
    public static final String COLONNE_MINUTE_CUISSON = "min_cuisson";
    public static final String COLONNE_DESCRIPTION = "description";
    public static final String COLONNE_UTILISATEUR = "utilisateur_id";

    public static final int NOM_MAX_CHARS = 64;

    public Recette(){
        /*no args constructor for ORMLite*/
    }

    public Recette(int difficulte, String nom, Utilisateur utilisateur) {
        this.difficulte = difficulte;
        this.nom = nom;
        this.utilisateur = utilisateur;
    }

    public Recette(String nom, int difficulte, int heurePreparation, int minutePreparation, int heureCuisson, int minuteCuisson, String description, Utilisateur utilisateur) {
        this.nom = nom;
        this.difficulte = difficulte;
        this.heurePreparation = heurePreparation;
        this.minutePreparation = minutePreparation;
        this.heureCuisson = heureCuisson;
        this.minuteCuisson = minuteCuisson;
        this.description = description;
        this.utilisateur = utilisateur;
    }

    @DatabaseField (generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false, columnName = COLONNE_NOM, width = NOM_MAX_CHARS)
    private String nom;

    @DatabaseField (canBeNull = true, columnName = COLONNE_DIFFICULTE)
    private int difficulte;

    @DatabaseField (canBeNull = true, columnName = COLONNE_HEURE_PREP)
    private int heurePreparation;

    @DatabaseField (canBeNull = true, columnName = COLONNE_MINUTE_PREP)
    private int minutePreparation;

    @DatabaseField (canBeNull = true, columnName = COLONNE_HEURE_CUISSON)
    private int heureCuisson;

    @DatabaseField (canBeNull = true, columnName = COLONNE_MINUTE_CUISSON)
    private int minuteCuisson;

    @DatabaseField (canBeNull = true, columnName = COLONNE_DESCRIPTION)
    private String description;

    @DatabaseField (
            canBeNull = false,
            foreign = true,
            foreignAutoRefresh = true,
            columnName = COLONNE_UTILISATEUR )
    private Utilisateur utilisateur;


    @Override
    public String toString() {
        return "Recette{" +
                "nom='" + nom + '\'' +
                ", difficulte=" + difficulte +
                ", utilisateur=" + utilisateur +
                '}';
    }
}
