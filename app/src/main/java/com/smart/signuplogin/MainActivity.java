package com.smart.signuplogin;

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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button signup;
    EditText username, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        signup = findViewById(R.id.button);
        username = findViewById(R.id.editTextText);
        pass = findViewById(R.id.editTextTextPassword);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = pass.getText().toString();
                if (password.length()>=8 && Validate(password)){
                    Intent it = new Intent(getBaseContext(), LoginActivity.class);
                    Bundle b = new Bundle();
                    b.putString("user",username.getText().toString());
                    b.putString("pass",password);
                    it.putExtras(b);
                    startActivity(it);
                }
                else {
                    Toast.makeText(getBaseContext(),"Not Valid",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean Validate(String password) {

        final String passwordpattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(passwordpattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}