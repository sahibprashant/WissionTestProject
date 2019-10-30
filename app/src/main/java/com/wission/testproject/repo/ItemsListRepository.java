package com.wission.testproject.repo;

import android.content.Context;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.wission.testproject.db.DAOs.ItemDao;
import com.wission.testproject.db.Entities.Items;
import com.wission.testproject.db.ItemsRoomDatabase;

import java.util.List;

public class ItemsListRepository {

    private ItemDao itemDao;
    private LiveData<List<Items>> itemsList;

    public ItemsListRepository(Context context){
        ItemsRoomDatabase db = ItemsRoomDatabase.getDatabase(context);
        itemDao = db.itemDao();
        itemsList = itemDao.getAllItems();
    }

    public LiveData<List<Items>> getItemsList() {
        return itemsList;
    }

    public void insert(Items item){
        new insertAsyncTask(itemDao).execute(item);
    }

    private static class insertAsyncTask extends AsyncTask<Items, Void , Void> {

        private ItemDao mItemDao;

        insertAsyncTask(ItemDao itemDao) {
            this.mItemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Items... items) {
            mItemDao.insert(items[0]);
            return null;
        }

    }
}
