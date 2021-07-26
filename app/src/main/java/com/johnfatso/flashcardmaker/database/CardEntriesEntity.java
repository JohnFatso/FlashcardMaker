package com.johnfatso.flashcardmaker.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Calendar;

@Entity(tableName = "card_entries")
public class CardEntriesEntity {
    @PrimaryKey
    @ColumnInfo(name = "unique_id")
    public int uniqueid;

    @ColumnInfo(name = "front_text")
    public String front_text;

    @ColumnInfo(name = "back_text")
    public String back_text;

    @ColumnInfo(name = "category")
    public String category;

    @ColumnInfo(name = "access_count")
    public int access_count;

    @ColumnInfo(name = "success_count")
    public int success_count;

    @ColumnInfo(name = "failure_count")
    public int failure_count;

    @ColumnInfo(name = "last_result")
    public int last_test_result;

    public CardEntriesEntity(int uniqueid, String front_text, String back_text, int access_count, int success_count, int failure_count, int last_test_result) {
        this.uniqueid = uniqueid;
        this.front_text = front_text;
        this.back_text = back_text;
        this.access_count = access_count;
        this.success_count = success_count;
        this.failure_count = failure_count;
        this.last_test_result = last_test_result;
    }

    public CardEntriesEntity() {
        this.uniqueid = (int)Calendar.getInstance().getTime().getTime();
        this.front_text = "";
        this.back_text = "";
        this.access_count = 0;
        this.success_count = 0;
        this.failure_count = 0;
        this.last_test_result = 0;
    }

    public int getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(int uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getFront_text() {
        return front_text;
    }

    public void setFront_text(String front_text) {
        this.front_text = front_text;
    }

    public String getBack_text() {
        return back_text;
    }

    public void setBack_text(String back_text) {
        this.back_text = back_text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAccess_count() {
        return access_count;
    }

    public void setAccess_count(int access_count) {
        this.access_count = access_count;
    }

    public int getSuccess_count() {
        return success_count;
    }

    public void setSuccess_count(int success_count) {
        this.success_count = success_count;
    }

    public int getFailure_count() {
        return failure_count;
    }

    public void setFailure_count(int failure_count) {
        this.failure_count = failure_count;
    }

    public int getLast_test_result() {
        return last_test_result;
    }

    public void setLast_test_result(int last_test_result) {
        this.last_test_result = last_test_result;
    }

    public void incrementSuccessCount(){
        this.access_count++;
        this.success_count++;
    }

    public void incrementFailureCount(){
        this.access_count++;
        this.failure_count++;
    }

}
