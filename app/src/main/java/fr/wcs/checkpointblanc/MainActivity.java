package fr.wcs.checkpointblanc;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText marque, name, immatriculation, kilometres;
    private Button send, voir;
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        marque = (EditText)findViewById(R.id.editTextMarque);
        name = (EditText)findViewById(R.id.editTextName);
        immatriculation = (EditText)findViewById(R.id.editTextImm);
        kilometres = (EditText)findViewById(R.id.editTextKilo);
        send = (Button)findViewById(R.id.sendButton);
        voir = (Button)findViewById(R.id.toutVoirButton);

        mDatabaseHelper = new DatabaseHelper(MainActivity.this);
        // Gets the data repository in write mode
        final SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(DatabaseContract.CarEntry.COLUMN_NAME_NAME, name.getText().toString());
                values.put(DatabaseContract.CarEntry.COLUMN_NAME_MARQUE, marque.getText().toString());
                values.put(DatabaseContract.CarEntry.COLUMN_NAME_IMMATRICULATION, immatriculation.getText().toString());
                values.put(DatabaseContract.CarEntry.COLUMN_NAME_KILOMETERS, Integer.parseInt(kilometres.getText().toString()));

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(DatabaseContract.CarEntry.TABLE_NAME, null, values);
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_LONG).show();
            }
        });

        voir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Define a projection that specifies which columns from the database
                // you will actually use after this query.
                String[] projection = {
                        DatabaseContract.CarEntry.COLUMN_NAME_IMMATRICULATION
                };

                Cursor cursor = db.query(
                        DatabaseContract.CarEntry.TABLE_NAME,     // The table to query
                        projection,                               // The columns to return
                        null,                                     // The columns for the WHERE clause
                        null,                                     // The values for the WHERE clause
                        null,                                     // don't group the rows
                        null,                                     // don't filter by row groups
                        null                                      // The sort order
                );

                while(cursor.moveToNext()) {
                    String immatriculation = cursor.getString(
                            cursor.getColumnIndexOrThrow(DatabaseContract.CarEntry.COLUMN_NAME_IMMATRICULATION));

                    Toast.makeText(MainActivity.this, immatriculation, Toast.LENGTH_LONG).show();
                }
                cursor.close();
            }
        });

    }

}