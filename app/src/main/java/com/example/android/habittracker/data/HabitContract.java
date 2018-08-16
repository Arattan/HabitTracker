package com.example.android.habittracker.data;

import android.provider.BaseColumns;

public final class HabitContract {

    public static abstract class HabitEntry implements BaseColumns{

        public static final String TABLE_NAME = "habits";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_GYM_VISIT = "gym";
        public static final int GYM_YES = 1;
        public static final int GYM_NO = 0;
        public static final String COLUMN_STEP_COUNT = "steps";
        public static final String COLUMN_PIZZA = "pizza";
        public static final int PIZZA_YES = 1;
        public static final int PIZZA_NO = 0;
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_CHEAT_DAY = "cheatDay";
        public static final int CHEAT_DAY_YES = 1;
        public static final int CHEAT_DAY_NO = 0;
    }
}
