package com.example.QueryBuilderExample;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "utilisateurs")
public class Utilisateur  {

    public static final String COLONNE_NOM = "nom";
    public static final int NOM_MAX_CHARS = 64;

    public static final String COLONE_PRENOM = "prenom";
    public static final int PRENOM_MAX_CHARS = 64;

    @DatabaseField (generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false, columnName = COLONNE_NOM, width = NOM_MAX_CHARS)
    private String nom;

    @DatabaseField (canBeNull = false, columnName = COLONE_PRENOM, width = PRENOM_MAX_CHARS)
    private String prenom;


    public Utilisateur(){
        /*no args constructor for ORMLite*/
    }

    public Utilisateur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
