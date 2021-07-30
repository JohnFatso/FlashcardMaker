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
    List<CardEntriesEntity> getAllCardsOfCategory(String category);

    @Query("SELECT DISTINCT category FROM card_entries ORDER BY category ASC")
    List<String> getListOfCategories();

    @Query("DELETE FROM card_entries")
    void deleteAll();

    @Query("DELETE FROM card_entries WHERE unique_id=:unique_id")
    void deleteEntry(int unique_id );

    @Query("DELETE FROM card_entries WHERE category=:category")
    void deleteCategory(String category);

    @Query("UPDATE card_entries SET front_text=:frontText, back_text=:backText WHERE unique_id=:primary_key")
    void updateCardEntryText(int primary_key, String frontText, String backText);

    @Query("UPDATE card_entries SET access_count=:accessCount, success_count=:successCount, failure_count=:failureCount WHERE unique_id=:unique_id")
    void updateCardEntryAccessCount(int unique_id, int accessCount, int successCount, int failureCount);

    @Query("UPDATE card_entries SET last_result=:last_results WHERE unique_id=:unique_id")
    void updateCardEntryLastResult(int unique_id, int last_results);

    @Query("UPDATE card_entries SET category=:newName WHERE category=:oldName")
    void updateCardCategoryName(String oldName, String newName);
}
