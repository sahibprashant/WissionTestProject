package com.wission.testproject.repo;

import android.content.Context;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.wission.testproject.db.ItemDao;
import com.wission.testproject.models.Items;
import com.wission.testproject.db.ItemsRoomDatabase;
import com.wission.testproject.util.Constants;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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

    private void insert(Items item){
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

    public void requestItems(){

        OkHttpClient client = new OkHttpClient();

        Request.Builder builder = new Request.Builder();
        builder.url(Constants.URL);
        Request request = builder.get().build();

        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200){
                itemDao.deleteAll();
                String res = Objects.requireNonNull(response.body()).string();
                JSONObject resObj = new JSONObject(res);
                Iterator it = resObj.keys();
                while (it.hasNext()){
                    String key = it.next().toString();
                    JSONObject obj = resObj.getJSONObject(key);
                    Iterator it1 = obj.keys();
                    String keyName = it1.next().toString();
                    String keyValue = obj.getString(keyName);
                    insert(new Items(null, keyName, keyValue));
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public void pushData(String key, String value){
        try {

            OkHttpClient client = new OkHttpClient();
            JSONObject obj = new JSONObject();
            obj.put(key, value);

            RequestBody body = RequestBody.create(obj.toString(), MediaType.parse("text/plain"));
            Request.Builder builder = new Request.Builder();
            builder.post(body);
            builder.url(Constants.URL);
            Request request = builder.build();

            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                //insert the added item to local db
                insert(new Items(null, key, value));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
