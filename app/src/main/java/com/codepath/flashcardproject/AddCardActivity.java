package com.codepath.flashcardproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        findViewById(R.id.myBtn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCardActivity.this, MainActivity.class); // create a new Intent, this is where we will put our data
                AddCardActivity.this.startActivityForResult(intent, 100);
                setResult(RESULT_OK, intent); // set result code and bundle data for response
                finish(); // closes this activity and pass data to the original activity that launched this activity
            }
        });

        findViewById(R.id.myBtn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCardActivity.this, MainActivity.class); // create a new Intent, this is where we will put our data
                intent.putExtra("Question", ((EditText) findViewById(R.id.Question)).getText().toString()); // puts one string into the Intent, with the key as 'string1'
                intent.putExtra("Answer", ((EditText) findViewById(R.id.Answer)).getText().toString()); // puts another string into the Intent, with the key as 'string2
                setResult(RESULT_OK, intent); // set result code and bundle data for response
                finish(); // closes this activity and pass data to the original activity that launched this activity
            }
        });
    }
}
