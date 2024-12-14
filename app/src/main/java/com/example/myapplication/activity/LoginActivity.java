package com.example.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.models.User.UserObject;
import com.example.myapplication.models.User.UserTable;

public class LoginActivity extends AppCompatActivity {
    public Button loginBtn;
    public EditText usernameTv;
    public EditText passwordTv;
    public CheckBox saveLoginCheckBox;
    public  UserTable userTable ;
    public static boolean isLogin = false;

    TextView signInIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);

        signInIntent = findViewById(R.id.signInIntent);
        loginBtn = findViewById(R.id.btn_login);
        usernameTv = findViewById(R.id.username_login);
        passwordTv = findViewById(R.id.password_login);
        saveLoginCheckBox = findViewById(R.id.checkBoxSaveLogin);
        userTable = new UserTable(LoginActivity.this);
        SharedPreferences sharedPreferences = getSharedPreferences("mySharedPrefer", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        signInIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = usernameTv.getText().toString();
                String passWord = passwordTv.getText().toString();
                boolean saveLogin = saveLoginCheckBox.isChecked();
                UserObject userObject = userTable.getUserByUserName(userName);
                if(userObject.passWord == null){
                    Toast.makeText(LoginActivity.this,"Tài khoản không tồn tại!" , Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!passWord.equals(userObject.passWord)){
                    Toast.makeText(LoginActivity.this,"Mật khẩu không chính xác!" , Toast.LENGTH_SHORT).show();
                    return;
                }
                if (saveLogin) {
                    editor.putInt("userID", userObject.userID);
                    int userID = sharedPreferences.getInt("userID", -1);
                    Toast.makeText(LoginActivity.this, "userID:Login: " + userID, Toast.LENGTH_SHORT).show();
                } else {
                    editor.remove("userID");
                }
                editor.apply();

                Intent i = new Intent(LoginActivity.this , MainActivity.class);
                startActivity(i);


            }
        });



    }

}
