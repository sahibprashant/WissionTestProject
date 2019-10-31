package com.wission.testproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wission.testproject.R;
import com.wission.testproject.adapters.ListItemAdapter;
import com.wission.testproject.interfaces.Callback;
import com.wission.testproject.viewModels.ItemsListViewModel;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener, Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton fab = findViewById(R.id.fab);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(getResources().getString(R.string.listActivityName));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ListItemAdapter adapter = new ListItemAdapter(this);
        adapter.setCallback(this);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(this);

        //initialise the view model and observe the data change
        ItemsListViewModel viewModel = ViewModelProviders.of(this).get(ItemsListViewModel.class);
        viewModel.getItemsList().observe(this, adapter::setData);
        new Thread(viewModel::requestData).start();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab){
            //start add item activity
            startActivity(new Intent(BaseActivity.this, AddItemActivity.class));
        }
    }

    @Override
    public void callBack(String key, String val) {
        //called on selecting an item from list
        Intent intent = new Intent(BaseActivity.this, AddItemActivity.class);
        intent.putExtra("type", "readOnly");
        intent.putExtra("itemName", val);
        intent.putExtra("itemKey", key);
        startActivity(intent);
    }
}
