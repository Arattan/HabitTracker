package com.example.android.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.android.habittracker.data.HabitContract.HabitEntry;

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = HabitDbHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "habits.db";
    private static final int DATABASE_VERSION = 1;
    /**
     * Database Helper enabling access to the database
     */
    public HabitDbHelper mDbHelper;

    public HabitDbHelper (Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
        }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(LOG_TAG, "TEST: onCreate called...");

        String SQL_CREATE_HABITS_TABLE =
                "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + HabitEntry.COLUMN_DATE + " TEXT NOT NULL,"
                + HabitEntry.COLUMN_GYM_VISIT + " INTEGER NOT NULL,"
                + HabitEntry.COLUMN_STEP_COUNT + " INTEGER NOT NULL,"
                + HabitEntry.COLUMN_PIZZA + " INTEGER NOT NULL,"
                + HabitEntry.COLUMN_WEIGHT + " REAL,"
                + HabitEntry.COLUMN_CHEAT_DAY + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_HABITS_TABLE);
        Log.v(LOG_TAG, SQL_CREATE_HABITS_TABLE);
    }

    public void insertHabit(String date, int gym, int steps, int pizza, double weight, int cheatDay) {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and habit attributes are the values.
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_DATE, date);
        values.put(HabitEntry.COLUMN_GYM_VISIT, gym);
        values.put(HabitEntry.COLUMN_STEP_COUNT, steps);
        values.put(HabitEntry.COLUMN_PIZZA, pizza);
        values.put(HabitEntry.COLUMN_WEIGHT, weight);
        values.put(HabitEntry.COLUMN_CHEAT_DAY, cheatDay);

        // Insert a new row for a daily habit record into the database,
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Log.v(LOG_TAG, "Error in insertHabit method");
        } else {
            Log.v(LOG_TAG, "Habit has been recorded");
        }
    }

    public Cursor readHabit() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                HabitEntry.COLUMN_DATE,
                HabitEntry.COLUMN_GYM_VISIT,
                HabitEntry.COLUMN_STEP_COUNT,
                HabitEntry.COLUMN_PIZZA,
                HabitEntry.COLUMN_WEIGHT,
                HabitEntry.COLUMN_CHEAT_DAY
        };

        Cursor cursor = db.query(HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
