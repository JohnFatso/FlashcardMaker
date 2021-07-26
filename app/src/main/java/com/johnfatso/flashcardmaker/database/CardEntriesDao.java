package com.johnfatso.flashcardmaker.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CardEntriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CardEntriesEntity cardEntriesEntity);

    @Query("SELECT * FROM card_entries ORDER BY unique_id ASC")
    List<CardEntriesEntity> getAllCards();

    @Query("SELECT * FROM card_entries WHERE category=:category ORDER BY unique_id ASC")
    List<CardEntriesEntity> getAllCardOfCategory(String category);

    @Query("DELETE FROM card_entries")
    void deleteAll();

    @Query("DELETE FROM card_entries WHERE unique_id=:unique_id")
    void deleteEntry(int unique_id );
}
