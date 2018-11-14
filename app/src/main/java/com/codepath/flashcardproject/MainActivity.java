package com.codepath.flashcardproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(this);// read data
        allFlashcards = flashcardDatabase.getAllCards();// read data

    findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
        }
    });

    findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            findViewById(R.id.flashcard_answer).setVisibility(View.GONE);
        }
    });

    findViewById(R.id.myBtn).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,AddCardActivity.class);
            MainActivity.this.startActivityForResult(intent, 100);
        }
    });

    flashcardDatabase = new FlashcardDatabase(getApplicationContext());
    if (allFlashcards != null && allFlashcards.size() > 0) {
        ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
        ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
    }// new flashcard activity

        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                // set the question and answer TextViews with data from the database
                ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
            }
        });

    }// End on Create
    int currentCardDisplayedIndex = 0;


    FlashcardDatabase flashcardDatabase; //Database access
    List<Flashcard> allFlashcards; // Save flashcards
   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
          String Question = Objects.requireNonNull(data.getExtras()).getString("Question"); // 'string1' needs to match the key we used when we put the string in the Intent
          String Answer = data.getExtras().getString("Answer");
          //Update new question and new answer
          ((TextView) findViewById(R.id.flashcard_question)).setText(Question);
          ((TextView) findViewById(R.id.flashcard_answer)).setText(Answer);

          flashcardDatabase.insertCard(new Flashcard(Question, Answer));
          allFlashcards = flashcardDatabase.getAllCards(); //flashcards updates
      }
    }
}
