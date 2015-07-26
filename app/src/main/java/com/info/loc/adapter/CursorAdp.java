package com.info.loc.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.info.loc.R;

/**
 * Created by ranjan on 7/25/2015.
 */
public class CursorAdp extends CursorAdapter {
    public CursorAdp(Context context, Cursor cursor)
    {
        super(context,cursor,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.home_list_item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv=(TextView)view.findViewById(R.id.tv);
        TextView tv2=(TextView)view.findViewById(R.id.tv2);
        ImageView iv=(ImageView)view.findViewById(R.id.iv);

        String title=cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String review=cursor.getString(cursor.getColumnIndexOrThrow("review"));
       //String imageUri=cursor.getString(cursor.getColumnIndex("image"));
       // Uri uri= Uri.parse(imageUri);
        tv.setText(title);
        tv2.setText(review);
        //Bitmap myBitmap = BitmapFactory.decodeFile(String.valueOf(uri));
        //iv.setImageBitmap(myBitmap);
    }
}
