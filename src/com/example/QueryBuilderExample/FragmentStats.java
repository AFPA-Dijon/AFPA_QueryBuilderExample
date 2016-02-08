package com.example.QueryBuilderExample;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;

/**
 * Created by sam on 05/02/2016.
 */
public class FragmentStats extends DatabaseHelperFragment {

    private TextView tvNomUtilisateur;
    private TextView tvTotalRecettes;
    private TextView facile, intermediaire, difficile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats_layout, container, false);
        this.tvNomUtilisateur = (TextView) view.findViewById(R.id.nomUtilisateur);
        this.tvTotalRecettes = (TextView) view.findViewById(R.id.totalRecettes);
        this.facile = (TextView) view.findViewById(R.id.facileCount);
        this.intermediaire = (TextView) view.findViewById(R.id.intermediaireCount);
        this.difficile = (TextView) view.findViewById(R.id.difficileCount);

        return view;
    }



    public void updateStats(Utilisateur utilisateur){
        Log.e("FACILE",this.facile.toString());
        this.tvNomUtilisateur.setText(utilisateur.getNom());
        this.tvTotalRecettes.setText(String.valueOf(countRecettesForUtilisateur(utilisateur)));
        try {

            Dao<Recette, Integer> dao = getHelper().getDaoRecette();
            this.facile.setText(""+countRecettesByDifficultyAndUser(utilisateur, Difficulte.FACILE));
            this.intermediaire.setText(""+countRecettesByDifficultyAndUser(utilisateur, Difficulte.INTERMEDIAIRE));
            this.difficile.setText(""+countRecettesByDifficultyAndUser(utilisateur, Difficulte.DIFFICILE));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public int countRecettesByDifficultyAndUser(Utilisateur utilisateur, int difficulte){
        int count = 0;
        try {
            QueryBuilder<Recette, Integer> builder = getHelper().daoRecette.queryBuilder();
            builder.selectRaw("COUNT("+Recette.COLONNE_UTILISATEUR+")")
                    .groupBy(Recette.COLONNE_UTILISATEUR)
                    .where().eq(Recette.COLONNE_DIFFICULTE, difficulte)
                    .and().eq(Recette.COLONNE_UTILISATEUR, utilisateur);
             count = (int) getHelper().getDaoRecette().queryRawValue(builder.prepareStatementString());


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return count;
        }
    }


    public int countRecettesForUtilisateur(Utilisateur utilisateur){
        int totalRecettes = 0;
        try {
           /* QueryBuilder<Recette, Integer> builder = getHelper().getDaoRecette().queryBuilder();
            builder.selectRaw("COUNT("+Recette.COLONNE_UTILISATEUR+")");
            builder.groupBy(Recette.COLONNE_UTILISATEUR);
            builder.where().eq(Recette.COLONNE_UTILISATEUR, utilisateur);
            i = (int) getHelper().getDaoRecette().queryRawValue(builder.prepareStatementString());
*/
            QueryBuilder<Recette, Integer> builder = getHelper().getDaoRecette().queryBuilder();
            totalRecettes = (int) builder.where().eq(Recette.COLONNE_UTILISATEUR, utilisateur).countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return totalRecettes;
        }
    }

}
