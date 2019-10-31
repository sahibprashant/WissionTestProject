package com.wission.testproject.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wission.testproject.models.Items;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert
    void insert(Items item);

    @Query("SELECT * FROM items_table ORDER BY id DESC")
    LiveData<List<Items>> getAllItems();

    @Query("DELETE FROM items_table")
    void deleteAll();

}
