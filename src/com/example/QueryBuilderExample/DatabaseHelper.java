package com.example.QueryBuilderExample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by sam on 04/02/2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "recettecuisine";
    public static final int DATABASE_VERSION = 2;

    public Dao<Utilisateur,  Integer> daoUtilisateur;
    public Dao<Recette,  Integer> daoRecette;

    public DatabaseHelper(Context context){
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Utilisateur.class);
            TableUtils.createTable(connectionSource, Recette.class);
            createTestData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTestData() throws SQLException {
        getDaoUtilisateur().create(new Utilisateur("RUSSELL", "Kurt"));
        getDaoUtilisateur().create(new Utilisateur("SOLID", "Snake"));
        List<Utilisateur> users = getDaoUtilisateur().queryForAll();
        Utilisateur kurt = users.get(0);
        Utilisateur snake = users.get(1);

        getDaoRecette().create(new Recette(Difficulte.FACILE,"Recette facile",  kurt ));
        getDaoRecette().create(new Recette(Difficulte.DIFFICILE,"Recette difficile",  kurt ));
        getDaoRecette().create(new Recette(Difficulte.FACILE,"Recette facile",  kurt ));

        getDaoRecette().create(new Recette(Difficulte.DIFFICILE, "Recette difficile",  snake ));
        getDaoRecette().create(new Recette(Difficulte.DIFFICILE, "Recette difficile",  snake ));
        getDaoRecette().create(new Recette(Difficulte.DIFFICILE, "Recette difficile",  snake ));
        getDaoRecette().create(new Recette(Difficulte.INTERMEDIAIRE, "Recette intermedaire",  snake ));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Recette.class, true);
            TableUtils.dropTable(connectionSource, Utilisateur.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Utilisateur, Integer> getDaoUtilisateur() throws SQLException {
        if (daoUtilisateur == null){
            daoUtilisateur = getDao(Utilisateur.class);
        }
        return daoUtilisateur;
    }

    public Dao<Recette, Integer> getDaoRecette() throws SQLException {
        if (daoRecette == null){
            daoRecette = getDao(Recette.class);
        }
        return daoRecette;
    }

}
