package com.info.loc.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.info.loc.R;
import com.info.loc.adapter.DbAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddPlaceActivity extends ActionBarActivity {

    private ListView listView;

    DbAdapter db;
    GPSTracker mGPSTracker;
    private static final String TAG_LOG = "add_place_activity";

    private TextView mTvLongitude;
    private TextView mTvlatitude;
    private Button mCamera;
    private EditText mEtTitle;
    private EditText mEtReview;
    private Spinner mCategory;
    private ImageButton mBtnSave;
    Double  latitude;
    Double  longitude;
    String imageUri;
    String category;
   @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent intent)
    {
        if(requestCode==1) {
            super.onActivityResult(requestCode, resultCode, intent);
            try {
                if (requestCode == 1 && resultCode == RESULT_OK && null != intent) {
                    Bitmap bm = (Bitmap) intent.getExtras().get("data");
                    BitmapDrawable bdrawable = new BitmapDrawable(this.getResources(), bm);
                    MediaStore.Images.Media.insertImage(getContentResolver(), bm, null, null);
                    mCamera.setBackgroundDrawable(bdrawable);
                    Uri u = intent.getData();
                    imageUri=u.toString();
                } else {
                    Toast.makeText(getBaseContext(), "PLEASE CAPTURE AN IMAGE", Toast.LENGTH_LONG).show();
                }
            } catch (Exception ex) {


            }

        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DbAdapter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        //dbAdapter = new DbAdapter(this);
        Log.d("save","saved");


       // ArrayList arrayList = dbAdapter.insertData(?,?,?,);
       // ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.activity_add_place);



        mTvLongitude = (TextView) findViewById(R.id.tvLon);
        mTvlatitude = (TextView) findViewById(R.id.tvLat);
        mCamera = (Button) findViewById(R.id.mCamera);
        mEtTitle = (EditText) findViewById(R.id.etTitle);
        mEtReview = (EditText) findViewById(R.id.etReview);
        mBtnSave = (ImageButton) findViewById(R.id.btnSave);

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TITLE= mEtTitle.getText().toString();
                String REVIEW=mEtReview.getText().toString();

                try {
                    db.open();
                 long l =   db.insertData(String.valueOf(latitude), String.valueOf(longitude), TITLE, REVIEW, category, imageUri);
                  Log.d("Nitin",""+(String.valueOf(l)));
                    db.close();
                    Toast.makeText(getApplicationContext(),"Data saved",Toast.LENGTH_LONG).show();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        mCategory = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCategory.setAdapter(adapter);
        category = mCategory.getSelectedItem().toString();

        mGPSTracker = new GPSTracker(this);
        if(mGPSTracker.canGetLocation()){
            latitude = mGPSTracker.getLatitude();
            longitude = mGPSTracker.getLongitude();

            mTvLongitude.setText(String.valueOf(longitude));
            mTvlatitude.setText(String.valueOf(latitude));

            // \n is for new line
            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            mGPSTracker.showSettingsAlert();
        }

    }

    public void navigate(View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI.getPath());
        startActivityForResult(intent, 1);
    }


}
