package fr.wcs.checkpointblanc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Car.db";

    private static final String SQL_CREATE_CAR =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContract.CarEntry.TABLE_NAME + " (" +
                    DatabaseContract.CarEntry._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.CarEntry.COLUMN_NAME_ID + " TEXT," +
                    DatabaseContract.CarEntry.COLUMN_NAME_NAME + " TEXT," +
                    DatabaseContract.CarEntry.COLUMN_NAME_MARQUE + " TEXT," +
                    DatabaseContract.CarEntry.COLUMN_NAME_IMMATRICULATION + " TEXT," +
                    DatabaseContract.CarEntry.COLUMN_NAME_KILOMETERS + " INTEGER)";

    private static final String SQL_DELETE_CAR =
            "DROP TABLE IF EXISTS " + DatabaseContract.CarEntry.TABLE_NAME;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_CAR);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_CAR);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
