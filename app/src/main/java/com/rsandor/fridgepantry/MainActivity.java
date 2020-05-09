package com.rsandor.fridgepantry;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ItemViewModel mItemViewModel;
    private Button addButton;
    private Button remButton;

    private static final String TAG = "mainActivity";

    private static final int ADD_ITEM_ACTIVITY_REQUEST_CODE = 1;

    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ItemListAdapter mAdapter;

        addButton = findViewById(R.id.addButton);
        remButton = findViewById(R.id.remButton);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //recyclerView.setNestedScrollingEnabled(false);
        //recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final ItemListAdapter adapter = new ItemListAdapter(this);
        mAdapter = adapter;
        recyclerView.setAdapter(adapter);

        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);


        mItemViewModel.getAllItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable final List<Item> items) {
                adapter.setItems(items);
            }
        });


       /*
            When the user selects to add an item. We should bring the user to the add item
            activity. From there this will add item to grocery list.
         */
        addButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Log.d(TAG, "onClick");
                Intent intent= new Intent(MainActivity.this, AddItemActivity.class);
                startActivityForResult(intent, ADD_ITEM_ACTIVITY_REQUEST_CODE);
            }
        });

        remButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mItemViewModel.deleteAll();
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check that it is the SecondActivity with an OK result
        if (requestCode == ADD_ITEM_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                /* Get String data from Intent
                   gl- Grocery list to distinguish from il (inventory list)
                */
                String  gl_list_name = data.getStringExtra("glName");
                int  gl_quantity     = data.getIntExtra("glQuantity", 0);
                String  gl_unit      = data.getStringExtra("glUnit");


                Item newItem = new Item(gl_list_name, gl_quantity, gl_unit);
                // Set text view with string
                mItemViewModel.insert(newItem);
            }
        }
    }
}
