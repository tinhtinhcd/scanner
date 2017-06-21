package org.lvt.quickscanner.Fragments;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.lvt.quickscanner.Database.DataHelper;

/**
 * Created by ylt1hc on 6/19/2017.
 */
public class BaseFragment extends Fragment {

    DataHelper dataHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupData();
    }

    public void setupData(){
        dataHelper = new DataHelper(getActivity().getBaseContext());
        sqLiteDatabase = dataHelper.getWritableDatabase();
    }

}
