package com.info.loc.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.info.loc.R;
import com.info.loc.adapter.CursorAdp;
import com.info.loc.adapter.DbAdapter;

import java.sql.SQLException;


public class FoodDrinks extends Fragment {
    ListView foodListView;
    DbAdapter db;
    Cursor cursor;

    public FoodDrinks() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        try {
            db.open();
            cursor=db.getDataCategory("Food n Drinks");
            Log.d("pallav", "" + cursor.getCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CursorAdp cursorAdp= new CursorAdp(getActivity(),cursor);
        foodListView = (ListView) rootView.findViewById(R.id.lv_food);
        foodListView.setAdapter(cursorAdp);*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // db = new DbAdapter(getActivity());
        View rootView = inflater.inflate(R.layout.fragment_food_drink, container, false);
        /*
        try {
            db.open();
            cursor=db.getDataCategory("Food n Drinks");
            Log.d("pallav", "" + cursor.getCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CursorAdp cursorAdp= new CursorAdp(getActivity(),cursor);
        foodListView = (ListView) rootView.findViewById(R.id.lv_food);
        foodListView.setAdapter(cursorAdp);*/
        return rootView;


    }



}
