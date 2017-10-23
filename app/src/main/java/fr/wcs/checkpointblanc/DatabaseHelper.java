package fr.wcs.checkpointblanc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Car.db";

    public static final String SQL_CREATE_CAR =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContract.CarEntry.TABLE_NAME + " (" +
                    DatabaseContract.CarEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseContract.CarEntry.COLUMN_NAME_NAME + " TEXT NOT NULL," +
                    DatabaseContract.CarEntry.COLUMN_NAME_MARQUE + "TEXT NOT NULL," +
                    DatabaseContract.CarEntry.COLUMN_NAME_KILOMETERS + "INTEGER," +
                    DatabaseContract.CarEntry.COLUMN_NAME_IMMATRICULATION + " INTEGER UNIQUE NOT NULL";

    public static final String SQL_DELETE_CAR =
            "DROP TABLE IF EXISTS " + DatabaseContract.CarEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CAR);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_CAR);
        onCreate(db);
    }
}
