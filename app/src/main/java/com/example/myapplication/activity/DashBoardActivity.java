package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
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
    ImageButton loginBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dash_board);

        subjectBtn = findViewById(R.id.subjectBtn);
        loginBackBtn = findViewById(R.id.loginBackBtn);

        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId", -1);
        Log.d("User:", userId + "");


        //Xem danh sách môn học
        subjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(DashBoardActivity.this, SubjectActivity.class);
                    i.putExtra("userId", userId);
                    startActivity(i);
                }
                catch (Exception e) {
                    Toast.makeText(DashBoardActivity.this, "Không thể chuyển hướng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Trở về đăng nhập
        loginBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    finish();
                }
                catch (Exception e) {
                    Toast.makeText(DashBoardActivity.this, "Không thể chuyển hướng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}