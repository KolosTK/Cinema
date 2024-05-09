package com.example.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cinema.R;

public class LoginActivity extends AppCompatActivity {
private EditText userEdit,passEdit;
private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        initView();
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    private void initView() {
        userEdit=findViewById(R.id.editTextUsername);
        passEdit=findViewById(R.id.editTextPassword);
        loginButton=findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(userEdit.getText().toString().isEmpty()||passEdit.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,"Fill all info", Toast.LENGTH_SHORT).show();
                }
                else if(userEdit.getText().toString().equals("test")&&passEdit.getText().toString().equals("1111")){
                    startActivity(new Intent (LoginActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this,"Password and usernem are incorrect",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}