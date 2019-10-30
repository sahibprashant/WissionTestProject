package com.wission.testproject.viewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.wission.testproject.db.Entities.Items;
import com.wission.testproject.repo.ItemsListRepository;
import java.util.List;

public class ItemsListViewModel extends AndroidViewModel {

    private ItemsListRepository repository;
    private LiveData<List<Items>> itemsList;

    public ItemsListViewModel(@NonNull Application application) {
        super(application);
        repository = new ItemsListRepository(application);
        itemsList = repository.getItemsList();
    }

    public void insert(Items item){
        repository.insert(item);
    }

    public LiveData<List<Items>> getItemsList(){return itemsList;}

}
