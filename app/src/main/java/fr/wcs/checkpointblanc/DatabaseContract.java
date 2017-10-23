package fr.wcs.checkpointblanc;

import android.provider.BaseColumns;

/**
 * Created by apprenti on 23/10/17.
 */

public class DatabaseContract {

    private DatabaseContract() {}

    public static class CarEntry implements BaseColumns {
        public static final String TABLE_NAME = "car";
        public static final String COLUMN_NAME_ID = "ID";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_IMMATRICULATION = "immatriculation";
        public static final String COLUMN_NAME_MARQUE = "marque";
        public static final String COLUMN_NAME_KILOMETERS = "kilometers";
    }
}
