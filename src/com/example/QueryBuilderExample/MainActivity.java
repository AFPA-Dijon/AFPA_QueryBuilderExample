package com.example.QueryBuilderExample;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity implements FragmentListe.FragmentListeListener{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public void OnUserSelected(Utilisateur choix) {

        /*mise à jour de la liste en fonction du choix dans le spinner*/
        FragmentManager fm = getFragmentManager();
        FragmentListe fl = (FragmentListe) fm.findFragmentById(R.id.fragmentListe);
        fl.updateList(choix);

        /*mise à jour des statistiques en fonction du choix dans le spinner*/
        FragmentStats fs = (FragmentStats) fm.findFragmentById(R.id.fragmentStats);
        fs.updateStats(choix);

    }











}
