package com.wission.testproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.wission.testproject.R;
import com.wission.testproject.db.Entities.Items;
import com.wission.testproject.interfaces.Callback;

import java.util.List;

public class ListItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private Callback callback;
    private List<Items> itemsList;

    public ListItemAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<Items> itemsList){
        this.itemsList = itemsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vHolder, int position) {
        if (itemsList != null) {
            ListItemHolder holder = (ListItemHolder) vHolder;
            String name = (position + 1) + ". " + itemsList.get(position).getItemName();
            holder.itemName.setText(name);
            holder.mainLayout.setOnClickListener(v -> {
                callback.callBack(itemsList.get(position).getItemName());
            });
        }else{
            //data is not ready yet.
        }
    }

    @Override
    public int getItemCount() {
        if(itemsList!=null){
            return itemsList.size();
        }else{
            return 0;
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    private class ListItemHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        ConstraintLayout mainLayout;
        ListItemHolder(View view) {
            super(view);
            mainLayout = view.findViewById(R.id.mainLayout);
            itemName = view.findViewById(R.id.itemName);
        }
    }
}
