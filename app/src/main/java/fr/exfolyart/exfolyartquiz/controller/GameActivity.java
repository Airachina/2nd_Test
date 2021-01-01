package fr.exfolyart.exfolyartquiz.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import fr.exfolyart.exfolyartquiz.R;
import fr.exfolyart.exfolyartquiz.model.Question1;
import fr.exfolyart.exfolyartquiz.model.QuestionBank;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mQuestion;
    private Button mAnswerButton1 ;
    private Button mAnswerButton2 ;
    private Button mAnswerButton3 ;
    private Button mAnswerButton4 ;
    private QuestionBank mQuestionBank;
    private Question1 mCurrentQuestion;
    private int mScore;
    private int mNumberOfQuestions;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    private boolean mEnableTouchEvents;
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        System.out.println("GameActivity//onCreate()");

        mQuestionBank = this.generateQuestions();

        if (savedInstanceState != null)
        {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
        } else
        {
            mScore = 0;
            mNumberOfQuestions = 4;
        }

        mEnableTouchEvents = true;

        //Wire widgets
        mQuestion = findViewById(R.id.textView);
        mAnswerButton1 = findViewById(R.id.button);
        mAnswerButton2 = findViewById(R.id.button2);
        mAnswerButton3 = findViewById(R.id.button3);
        mAnswerButton4 = findViewById(R.id.button4);

        //Use de tag property to'name' the buttons
        mAnswerButton1.setTag(0);
        mAnswerButton2.setTag(1);
        mAnswerButton3.setTag(2);
        mAnswerButton4.setTag(3);

        mAnswerButton1.setOnClickListener(this);
        mAnswerButton2.setOnClickListener(this);
        mAnswerButton3.setOnClickListener(this);
        mAnswerButton4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestions);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();

        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            //Good Answer
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            mScore++;

        } else {
            //Wrong Answer
            Toast.makeText(this, "Faux", Toast.LENGTH_SHORT).show();
        }
        mEnableTouchEvents = false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;

                if (--mNumberOfQuestions == 0) //If this is the last question, ends the game.
                {
                    //End The Game
                    endGame();
                } else //Else, display the next question
                {
                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }
            }
        }, 2000); //LENGTH_SHORT is usually 2 second long
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    private void endGame()
    {   AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bien joué !")
                .setMessage("Votre score est de " + mScore)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {   @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    //End the activity
                    Intent intent = new Intent();
                    intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                })
                .create()
                .show();
    }
    private void displayQuestion(final Question1 question){
        mQuestion.setText(question.getQuestion());
        mAnswerButton1.setText(question.getChoiceList().get(0));
        mAnswerButton2.setText(question.getChoiceList().get(1));
        mAnswerButton3.setText(question.getChoiceList().get(2));
        mAnswerButton4.setText(question.getChoiceList().get(3));
    }
    private QuestionBank generateQuestions()
    {
        Question1 question1 = new Question1("Quel est le surnom de notre Trésorière ?",
                Arrays.asList("Bebette", "Babsy", "Babsoaille", "Babibelle"),
                1);
        Question1 question2 = new Question1("En quelle année fût créée Exfoly'Art' ?",
                Arrays.asList("2017", "2018", "2019", "2020"),
                3);
        Question1 question3 = new Question1("Lequel de ces pôles n'existe pas au sein de la Fédération ?",
                Arrays.asList("Découverte", "Artistique", "Cohésion", "Recherche"),
                0);
        Question1 question4 = new Question1("Comment se nomme le créateur du logo Exfoly'Art' ?",
                Arrays.asList("Deep All", "Depell", "Deppel", "Depal"),
                1);
        Question1 question5 = new Question1("Qui nous met à disposition un local ?",
                Arrays.asList("Valentin CHESNEY", "Chimi", "Le deuxième père de RJ Junior", "Le mec de Bibi"),
                0);
        Question1 question6 = new Question1("Sur quel principe architectural se base l'organisation de la Fédération ?",
                Arrays.asList("La Fenestration", "La Volumétrie", "La Tenségrité", "La Forme"),
                2);

        return new QuestionBank(Arrays.asList(
                question1,
                question2,
                question3,
                question4,
                question5,
                question6));
    }

    @Override
    protected void onStart(){
        super.onStart();

        System.out.println("GameActivity::onStart()");}

    @Override
    protected void onResume(){
        super.onResume();

        System.out.println("GameActivity::onResume()");}

    @Override
    protected void onPause(){
        super.onPause();

        System.out.println("GameActivity::onPause()");}

    @Override
    protected void onStop(){
        super.onStop();

        System.out.println("GameActivity::onStop()");}

    @Override
    protected void onDestroy(){
        super.onDestroy();

        System.out.println("GameActivity::onDestroy()");}
}