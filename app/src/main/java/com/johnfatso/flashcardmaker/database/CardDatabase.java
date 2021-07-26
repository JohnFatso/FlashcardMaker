package com.johnfatso.flashcardmaker.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CardEntriesEntity.class}, version = 1, exportSchema = true)
public abstract class CardDatabase extends RoomDatabase {

    public abstract CardEntriesDao cardEntriesDao();

    private static volatile CardDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CardDatabase getInstance(final Context context){
        if (INSTANCE == null) {
            synchronized (CardDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CardDatabase.class,
                            "word_database").build();
                }
            }
        }
        return INSTANCE;
    }

}
