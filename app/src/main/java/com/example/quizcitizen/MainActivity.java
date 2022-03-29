package com.example.quizcitizen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.quizcitizen.databinding.ActivityMainBinding;
import com.example.quizcitizen.databinding.ActivityMainBindingImpl;
import com.example.quizcitizen.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;


    private Question[] questionBank =  new Question[]{

            new Question(R.string.question_amendments , false),
            new Question(R.string.question_constitution , true),
            new Question(R.string.question_declaration , true),
            new Question(R.string.question_independence_rights , true),
            new Question(R.string.question_religion , true),
            new Question(R.string.question_government , false),
            new Question(R.string.question_government_feds , false),
            new Question(R.string.question_government_senators , false),
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main);
        binding.tvQuestion.setText(questionBank[currentQuestionIndex].getAnswerResId());

        binding.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length;
                updateQuestion();
            }
        });

        binding.btPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex > 0 ){
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.length;
                    updateQuestion();
                }
            }
        });

        binding.btTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        binding.btFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

    }

    private void checkAnswer(boolean userChoose){
        boolean answer = questionBank[currentQuestionIndex].isAnswerTrue();
        int messageId;
        if (answer == userChoose){
            messageId = R.string.correct_answer;
        }else {
            messageId = R.string.wrong_answer;
        }

        Snackbar.make(binding.imgAndroid , messageId , Snackbar.LENGTH_SHORT).show();

    }

    private void updateQuestion() {
            binding.tvQuestion.setText(questionBank[currentQuestionIndex].getAnswerResId());

    }
}