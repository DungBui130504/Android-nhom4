package com.example.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.models.User.UserTable;

public class SignInActivity extends AppCompatActivity {
    TextView loginIntent;
    public EditText txtRegUserName;
    public EditText txtRegPassWord;

    public Button btnReg;
    public UserTable userTable ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userTable = new UserTable(SignInActivity.this);
        SharedPreferences mySharedPrefer = getSharedPreferences("mySharedPrefer", MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPrefer.edit();

        txtRegUserName = findViewById(R.id.regTxtUserName);
        txtRegPassWord = findViewById(R.id.txtRegPassWord);
        btnReg = findViewById(R.id.btnReg);


        loginIntent = (TextView) findViewById(R.id.loginIntent);

        loginIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignInActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}