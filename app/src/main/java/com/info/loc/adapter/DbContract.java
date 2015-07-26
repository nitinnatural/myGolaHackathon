package com.info.loc.adapter;

import java.sql.Blob;

/**
 * Created by ranjan on 7/25/2015.
 */
public class DbContract {

    public static final class CSEntry {
     // conlumn names
        public static final String COLUMN_ID="_id";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_REVIEW = "review";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_CATEGORY = "category";
    }
}
