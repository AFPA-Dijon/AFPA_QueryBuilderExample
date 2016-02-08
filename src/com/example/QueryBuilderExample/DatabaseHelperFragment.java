package com.example.QueryBuilderExample;

import android.app.Fragment;
import com.j256.ormlite.dao.Dao;

/**
 * Created by sam on 05/02/2016.
 */
public class DatabaseHelperFragment extends Fragment {

    private static DatabaseHelper dbHelper;

    public DatabaseHelper getHelper(){
        if(dbHelper == null){
            dbHelper = new DatabaseHelper(getActivity());
        }
        return dbHelper;
    }

}
