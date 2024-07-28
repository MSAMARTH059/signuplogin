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

public class LoginActivity extends AppCompatActivity {

    EditText username,pass;
    Button logbtn;
    int counter=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = findViewById(R.id.editTextText);
        pass = findViewById(R.id.editTextTextPassword);
        logbtn = findViewById(R.id.button);

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = getIntent().getExtras();
                String user = b.getString("user");
                String spass = b.getString("pass");
                if (user.equals(username.getText().toString()) && spass.equals(pass.getText().toString())){
                    Intent it = new Intent(getBaseContext(), SuccessActivity.class);
                    startActivity(it);
                }
                else {
                    counter--;
                }
                if (counter==0){
                    Toast.makeText(getBaseContext(),"Failed Login attempts",Toast.LENGTH_LONG).show();
                    logbtn.setEnabled(false);
                }
            }
        });
    }
}