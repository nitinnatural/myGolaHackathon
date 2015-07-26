package com.info.loc.activity;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.info.loc.R;
import com.info.loc.adapter.CursorAdp;
import com.info.loc.adapter.DbAdapter;
import com.info.loc.adapter.DbContract;

import java.sql.SQLException;


public class HomeFragment extends Fragment {
    ListView mHomeListView;
    ImageView mAddButton;
    DbAdapter db;
    Cursor cursor;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new DbAdapter(getActivity());
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mAddButton = (ImageView) rootView.findViewById(R.id.ivAddNew);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddPlaceActivity.class);
                startActivity(intent);
            }
        });


       try {
            db.open();
            cursor=db.getAllData();
               Log.d ("pallav" , ""+cursor.getCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }



        CursorAdp cursorAdp= new CursorAdp(getActivity(),cursor);
        mHomeListView = (ListView) rootView.findViewById(R.id.lv_home);
        mHomeListView.setAdapter(cursorAdp);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    }
