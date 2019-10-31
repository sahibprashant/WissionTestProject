package com.wission.testproject.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.wission.testproject.R;
import com.wission.testproject.models.Items;

@Database(entities = {Items.class}, version = 1, exportSchema = false)
public abstract class ItemsRoomDatabase extends RoomDatabase {

    private static ItemsRoomDatabase databaseInstance;

    public abstract ItemDao itemDao();

     public static ItemsRoomDatabase getDatabase(Context context){
        if (databaseInstance == null){
            synchronized (ItemsRoomDatabase.class){
                if (databaseInstance == null){
                    databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                            ItemsRoomDatabase.class, context.getResources().getString(R.string.dbName))
                            //Will clear and rebuild the db if found version is not same
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return databaseInstance;
    }
}