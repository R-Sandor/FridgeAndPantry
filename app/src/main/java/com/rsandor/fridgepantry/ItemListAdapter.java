package com.rsandor.fridgepantry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.MyViewHolder> {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView itemNameTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemNameTextView =  (TextView) itemView.findViewById(R.id.myItem2);
        }
    }
    private final LayoutInflater mInflater;
    private List<Item> mItems;

    ItemListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    // Create new views (invoked by the layout manager)
    @Override
    public ItemListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_list_view, parent, false);
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (mItems != null) {
            Item current = mItems.get(position);
            holder.itemNameTextView.setText(current.getItemName());
        } else {
            // Covers the case of data not being ready yet.
            holder.itemNameTextView.setText("No Word");
        }
    }

    void setItems(List<Item> items){
        mItems = items;
        notifyDataSetChanged();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (mItems != null)
            return mItems.size();
        else return 0;
    }
}


