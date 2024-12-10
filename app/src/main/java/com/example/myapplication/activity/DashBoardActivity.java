package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class DashBoardActivity extends AppCompatActivity {
    LinearLayout subjectBtn, notificationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dash_board);

        subjectBtn = findViewById(R.id.subjectBtn);

        subjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(DashBoardActivity.this, SubjectActivity.class);
                    startActivity(i);
                }
                catch (Exception e) {
                    Toast.makeText(DashBoardActivity.this, "Không thể chuyển hướng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}