package com.example.android.habittracker;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

/**
 * Activity for inputting and reading new or existing habit data to the database
 */
public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();
    /**
     * Database Helper enabling access to the database
     */
    public HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiates the subclass of SQLiteOpenHelper
        //The current activity is the context parameter
        mDbHelper = new HabitDbHelper(this);

        mDbHelper.insertHabit("100818",
                HabitEntry.GYM_YES,
                14678,
                HabitEntry.PIZZA_NO,
                71.5,
                HabitEntry.CHEAT_DAY_NO);

        mDbHelper.insertHabit("110818",
                HabitEntry.GYM_NO,
                8716,
                HabitEntry.PIZZA_YES,
                71.4,
                HabitEntry.CHEAT_DAY_YES);
        
        Cursor queryCursor = mDbHelper.readHabit();
            Log.e(LOG_TAG, "TEST: Called on cursor instance");

        try {
            int idColumnIndex = queryCursor.getColumnIndex(HabitEntry._ID);
            int dateColumnIndex = queryCursor.getColumnIndex(HabitEntry.COLUMN_DATE);
            int gymColumnIndex = queryCursor.getColumnIndex(HabitEntry.COLUMN_GYM_VISIT);
            int stepsColumnIndex = queryCursor.getColumnIndex(HabitEntry.COLUMN_STEP_COUNT);
            int pizzaColumnIndex = queryCursor.getColumnIndex(HabitEntry.COLUMN_PIZZA);
            int weightColumnIndex = queryCursor.getColumnIndex(HabitEntry.COLUMN_WEIGHT);
            int cheatDayColumnIndex = queryCursor.getColumnIndex(HabitEntry.COLUMN_CHEAT_DAY);

            while (queryCursor.moveToNext()) {
                int currentId = queryCursor.getInt(idColumnIndex);
                String currentDate = queryCursor.getString(dateColumnIndex);
                int currentGym = queryCursor.getInt(gymColumnIndex);
                int currentSteps = queryCursor.getInt(stepsColumnIndex);
                int currentPizza = queryCursor.getInt(pizzaColumnIndex);
                double currentWeight = queryCursor.getDouble(weightColumnIndex);
                int currentCheatDay = queryCursor.getInt(cheatDayColumnIndex);

                Log.i(LOG_TAG, "Selected Records: " + currentId + currentDate + currentGym +
                        currentSteps + currentPizza + currentWeight + currentCheatDay);
            }
        } finally {
            queryCursor.close();
        }
    }
}






