package com.wission.testproject.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.wission.testproject.R;
import com.wission.testproject.db.Entities.Items;
import com.wission.testproject.viewModels.ItemsListViewModel;

import java.util.Objects;


public class AddItemActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private Button button;
    private ItemsListViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        init();
    }

    private void init() {
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        //if activity is opened to view item
        if(getIntent().getExtras()!=null  &&
                Objects.requireNonNull(getIntent().getStringExtra("type")).equalsIgnoreCase("readOnly")){
            String itemName = getIntent().getStringExtra("itemName");

            button.setVisibility(View.GONE);
            editText.setText(itemName);
            editText.setEnabled(false);
            return;
        }

        viewModel = ViewModelProviders.of(this).get(ItemsListViewModel.class);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button){
            String val = editText.getText().toString().trim();
            if (val.equalsIgnoreCase("")){
                Toast.makeText(this, getResources().getString(R.string.empty_field), Toast.LENGTH_SHORT).show();
            }else{
                //inserting the data
                viewModel.insert(new Items(null,val));
                Toast.makeText(this, getResources().getString(R.string.saved_successfully), Toast.LENGTH_SHORT).show();
                button.setEnabled(false);
            }
        }
    }
}
