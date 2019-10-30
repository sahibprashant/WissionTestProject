package com.wission.testproject.db.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wission.testproject.db.Entities.Items;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert
    void insert(Items item);

    @Query("SELECT * FROM items_table ORDER BY id DESC")
    LiveData<List<Items>> getAllItems();

}
