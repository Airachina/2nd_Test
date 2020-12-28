package fr.exfolyart.exfolyartquiz.controller;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.exfolyart.exfolyartquiz.R;
import fr.exfolyart.exfolyartquiz.model.User1;

public class MainActivity extends AppCompatActivity {

       private TextView mBienvenueText;
       private EditText mPrenomInput;
       private Button mPlayButton;
       private User1 mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      mUser = new User1();
      mBienvenueText = findViewById(R.id.textView2);
      mPrenomInput = findViewById(R.id.editTextTextPersonName2);
      mPlayButton = findViewById(R.id.activity_main_play_btn);

        mPlayButton.setEnabled(false);

        mPrenomInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() !=0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = mPrenomInput.getText().toString();
                mUser.setFirstname(firstname);
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameActivityIntent);
            }
        });
    }
}