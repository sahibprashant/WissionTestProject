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
import com.wission.testproject.viewModels.ItemsListViewModel;
import java.util.Objects;

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText key, value;
    private Button button;
    private ItemsListViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        init();
    }

    private void init() {

        key = findViewById(R.id.key);
        value = findViewById(R.id.val);
        button = findViewById(R.id.button);

        //if activity is opened to view item
        if(getIntent().getExtras()!=null  &&
                Objects.requireNonNull(getIntent().getStringExtra("type")).equalsIgnoreCase("readOnly")){

            button.setVisibility(View.GONE);
            key.setText(getIntent().getStringExtra("itemKey"));
            value.setText(getIntent().getStringExtra("itemName"));
            key.setEnabled(false);
            value.setEnabled(false);
            return;
        }

        viewModel = ViewModelProviders.of(this).get(ItemsListViewModel.class);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button){
            String val = value.getText().toString().trim();
            String keyName = key.getText().toString().trim();
            if (val.equalsIgnoreCase("") || keyName.equalsIgnoreCase("")){
                Toast.makeText(this, getResources().getString(R.string.empty_field), Toast.LENGTH_SHORT).show();
            }else{
                //inserting the data
                new Thread(() -> viewModel.insertData(keyName, val)).start();
                Toast.makeText(AddItemActivity.this, getResources().getString(R.string.saved_successfully), Toast.LENGTH_SHORT).show();
                button.setEnabled(false);
            }
        }
    }
}
