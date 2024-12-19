package com.example.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class AddQuestionActivity extends AppCompatActivity {
    ImageButton backBtn;
    EditText questionTxt, answserTxt;
    ImageButton submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        backBtn = findViewById(R.id.backBtn);
        questionTxt = findViewById(R.id.questionTxt);
        answserTxt = findViewById(R.id.answserTxt);
        submit = findViewById(R.id.submit);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    finish();
                }
                catch (Exception e) {
                    Toast.makeText(AddQuestionActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    
                }
                catch (Exception e) {
                    Toast.makeText(AddQuestionActivity.this, "Thêm không thành công!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(AddQuestionActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}