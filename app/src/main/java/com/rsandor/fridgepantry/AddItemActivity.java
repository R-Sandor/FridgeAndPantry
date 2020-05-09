package com.rsandor.fridgepantry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.content.ContentValues.TAG;

public class AddItemActivity extends Activity {

    private Item mItem;
    private Button acceptButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_activity);

        acceptButton = findViewById(R.id.acceptBtn);
        acceptButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Log.d(TAG, "accept item");
                // Open add item activity.
                EditText editText = (EditText) findViewById(R.id.item_name);
                String itemName = editText.getText().toString();

                editText = (EditText) findViewById(R.id.quantityEdit);
                int quantity = Integer.parseInt(editText.getText().toString());

                editText = (EditText) findViewById(R.id.unitTypeEdit);
                String unit = editText.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("glName", itemName);
                intent.putExtra("glQuantity", quantity);
                intent.putExtra("glUnit", unit);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    public void onButtonClick(View view) {

        // Get the text from the EditText
        EditText editText = (EditText) findViewById(R.id.item_name);
        String stringToPassBack = editText.getText().toString();

        // Put the String to pass back into an Intent and close this activity
        Intent intent = new Intent();
        intent.putExtra("keyName", stringToPassBack);
        setResult(RESULT_OK, intent);
        finish();
    }

}
