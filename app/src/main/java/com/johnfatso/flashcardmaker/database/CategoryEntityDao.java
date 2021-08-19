package com.johnfatso.flashcardmaker.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CategoryEntity... categoryEntity);

    @Query("SELECT * FROM category_entries ORDER BY unique_id ASC")
    List<CategoryEntity> getAllCategoryEntities();

    @Query("SELECT category FROM category_entries ORDER BY unique_id ASC")
    List<String> getAllCategoryNames();

    @Query("DELETE FROM category_entries WHERE unique_id=:uniqueId")
    void deleteCategoryByUniqueId(int uniqueId);

    @Query("DELETE FROM category_entries WHERE category=:categoryName")
    void deleteCategoryByName(String categoryName);

    @Query("DELETE FROM category_entries")
    void deleteAllCategories();

    @Query("UPDATE category_entries SET category=:categoryName where unique_id=:uniqueId")
    void updateCategoryName(String categoryName, int uniqueId);

    @Query("UPDATE category_entries SET category=:newName where category=:previousName")
    void updateCategoryName(String previousName, String newName);
}
