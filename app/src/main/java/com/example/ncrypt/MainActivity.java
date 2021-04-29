package com.example.ncrypt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    Button st;
    String name,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        st = (Button)findViewById(R.id.start);
        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = et1.getText().toString();
                pwd = et2.getText().toString();
                if(name.length()==0){
                    et1.requestFocus();
                    et1.setError("Field can't be empty");
                }
                else if(!name.matches("[a-zA-Z]*")){
                    et1.requestFocus();
                    et1.setError("Only alphabets allowed.");
                }
                else if(pwd.length()==0){
                    et2.requestFocus();
                    et2.setError("Field can't be empty");
                }
                else if(pwd.length()>10){
                    et2.requestFocus();
                    et2.setError("Max. 10 characters allowed");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Welcome " + name, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), Choice.class);
                    i.putExtra("nme", name);
                    startActivity(i);
                }
            }
        });
    }
}