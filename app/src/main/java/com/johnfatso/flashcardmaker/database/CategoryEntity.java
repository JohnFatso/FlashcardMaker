package com.johnfatso.flashcardmaker.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity(tableName = "category_entries", indices = {@Index(value = {"unique_id","category"}, unique = true)})
class CategoryEntity {
    @PrimaryKey
    @ColumnInfo(name = "unique_id")
    public int uniqueid;

    @ColumnInfo(name = "category")
    public String category;

    public CategoryEntity(int uniqueid, String category) {
        this.uniqueid = uniqueid;
        this.category = category;
    }

    @Ignore
    public CategoryEntity(String category) {
        this.uniqueid = (int) Calendar.getInstance().getTime().getTime();
        this.category = "category_name_here";
    }

    public int getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(int uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
