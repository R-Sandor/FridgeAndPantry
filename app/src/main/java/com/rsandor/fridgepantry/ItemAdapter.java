package com.rsandor.fridgepantry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private ArrayList<String> mDataset;

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

    // Provide a suitable constructor (depends on the kind of dataset)
    public ItemAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.item_list_view, parent, false);



        //...
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemNameTextView.setText(mDataset.get(position));

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }




}


