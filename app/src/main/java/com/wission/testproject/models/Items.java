package com.wission.testproject.models;

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

    @NonNull
    @ColumnInfo(name = "itemKey")
    private String itemKey;

    public Items(Long id, @NonNull String itemKey, @NonNull String itemName) {
        this.id = id;
        this.itemKey = itemKey;
        this.itemName = itemName;
    }

    @NonNull
    public String getItemName() {
        return itemName;
    }

    @NonNull
    public Long getId(){return id;}

    @NonNull
    public String getItemKey() {
        return itemKey;
    }
}