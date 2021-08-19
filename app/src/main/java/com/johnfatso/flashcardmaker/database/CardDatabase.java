package com.johnfatso.flashcardmaker.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CardEntriesEntity.class, CategoryEntity.class}, version = 1, exportSchema = true)
public abstract class CardDatabase extends RoomDatabase {

    public abstract CardEntriesDao cardEntriesDao();
    public abstract CategoryEntityDao categoryEntityDao();
    private static final String LOG_TAG = "DATABASE";

    private static volatile CardDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CardDatabase getInstance(final Context context){
        Log.v(LOG_TAG, CardDatabase.class.getName()+" | instance requested");
        if (INSTANCE == null) {
            synchronized (CardDatabase.class) {
                if (INSTANCE == null) {
                    Log.v(LOG_TAG, CardDatabase.class.getName()+" | new instance to be created");
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CardDatabase.class,
                            "word_database").build();
                    Log.v(LOG_TAG, CardDatabase.class.getName()+" | new instance created");
                }
            }
        }
        Log.v(LOG_TAG, CardDatabase.class.getName()+" | instance available");
        return INSTANCE;
    }

}
