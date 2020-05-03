package com.rsandor.fridgepantry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private Button addButton;
    private Button remButton;
    private static final String TAG = "mainActivity";
    private ArrayList<String> mItems;
    private static final int ADD_ITEM_ACTIVITY_REQUEST_CODE = 0;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
       // Fragment fragment = fm.findFragmentById(R.id.fragment_container);

//        if (fragment == null) {
//            fragment = new AddItemActivity();
//            fm.beginTransaction()
//                    .add(R.id.fragment_container,fragment)
//                    .commit();
//        }

        //TODO:
        // This will be come a list of items at some point.
        mItems = new ArrayList<>(Arrays.asList("Beans", "Butter"));


        addButton = findViewById(R.id.addButton);
        remButton = findViewById(R.id.remButton);

        /*
            When the user selects to add an item. We should bring the user to the add item
            activity. From there this will add item to grocery list.
         */
        addButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Log.d(TAG, "onClick");
                // Open add item activity.

                Intent intent= new Intent(MainActivity.this, AddItemActivity.class);
                startActivityForResult(intent, ADD_ITEM_ACTIVITY_REQUEST_CODE);
            }
        });



        remButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Log.d(TAG, "onRem");
                // Open add item activity.
                mAdapter.notifyItemRemoved(0);
                if(mItems.size() > 0)
                    mItems.remove(0);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ItemAdapter(mItems);
        recyclerView.setAdapter(mAdapter);


    }

    private void addItem()
    {
        mAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == ADD_ITEM_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                // Gl - Grocery list to distinguish from Il (inventory list)
                String  gl_list_name    = data.getStringExtra("glName");
                double  gl_quantity     = data.getDoubleExtra("glQuantity", 0);
                String  gl_unit         = data.getStringExtra("glUnit");
                Log.d(TAG, gl_list_name + " " + gl_quantity + " " + gl_unit);

                // Set text view with string
                mItems.add(gl_list_name);
                addItem();
            }
        }
    }



}
