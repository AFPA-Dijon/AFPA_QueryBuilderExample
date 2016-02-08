package com.example.QueryBuilderExample;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 05/02/2016.
 */
public class FragmentListe extends DatabaseHelperFragment implements AdapterView.OnItemSelectedListener{

    private FragmentListeListener listener;
    private ArrayAdapter<Recette> recettesAdapter;
    private ArrayAdapter<Utilisateur> utilisateurAdapter;
    private Spinner spinnerUtilisateurs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liste_layout, container, false);

        ListView listeRecettes =
                (ListView) view.findViewById(R.id.listViewRecettes);
        this.spinnerUtilisateurs =
                (Spinner) view.findViewById(R.id.spinnerUtilisateurs);
        try {
            this.utilisateurAdapter = new ArrayAdapter<Utilisateur>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                getHelper().getDaoUtilisateur().queryForAll()
            );
            spinnerUtilisateurs.setAdapter(utilisateurAdapter);
            spinnerUtilisateurs.setOnItemSelectedListener(this);

            this.recettesAdapter = new ArrayAdapter<Recette>(
                    getActivity(),
                    android.R.layout.simple_list_item_1);
            this.updateList((Utilisateur) this.spinnerUtilisateurs.getSelectedItem());

            listeRecettes.setAdapter(recettesAdapter);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return view;
    }

    /*mise à jour de la listview en fonction de la selection d'utilisateur dans le spinner*/
    public void updateList(Utilisateur utilisateurSelectionne){
        this.recettesAdapter.clear();
        this.recettesAdapter.addAll(
                getRecettesByUtilisateur(utilisateurSelectionne)
        );
    }

    @Override
    public void onItemSelected(
            AdapterView<?> parent, View view, int position, long id) {
        this.listener.OnUserSelected(this.utilisateurAdapter.getItem(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //nothing TODO
    }

    public interface FragmentListeListener {
        void OnUserSelected(Utilisateur choix);
    }

    @Override
    public void onAttach(Activity activity) {
        if(activity instanceof FragmentListeListener){
            this.listener = (FragmentListeListener) activity;
        } else {
            throw new ClassCastException(
                    "L'activité : "+activity.getClass().getSimpleName()
                    + "doit implémenter l'interface FragmentListe.FragmentListeListener"
            );
        }
        super.onAttach(activity);
    }

    public List<Recette> getRecettesByUtilisateur(Utilisateur utilisateur){
        List<Recette> recettes = new ArrayList<>();
        try {
            QueryBuilder<Recette, Integer> queryBuilder = getHelper().getDaoRecette().queryBuilder();
            queryBuilder.where().eq(Recette.COLONNE_UTILISATEUR, utilisateur);
            PreparedQuery<Recette> preparedQuery = queryBuilder.prepare();
            recettes = getHelper().getDaoRecette().query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return recettes;
        }
    }
}
