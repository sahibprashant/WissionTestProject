package com.wission.testproject.db.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items_table")
public class Items {

    @NonNull
    @ColumnInfo(name = "itemName")
    private String itemName;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long id;

    public Items(Long id, @NonNull String itemName) {
        this.itemName = itemName;
        this.id = id;
    }

    @NonNull
    public String getItemName() {
        return itemName;
    }

    @NonNull
    public Long getId(){return id;}

}