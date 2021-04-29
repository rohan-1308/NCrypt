package com.example.ncrypt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Choice extends AppCompatActivity {
    RadioButton cs,sha256,sha512,md5;
    RadioGroup choice;
    Button start;
    EditText etv2;
    String str="",name,hashtext="";
    TextView tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        TextView nm = (TextView)findViewById(R.id.dname);
        Intent i = getIntent();
        name = i.getStringExtra("nme");
        nm.setText("Name: "+name);
        etv2 = findViewById(R.id.etv2);
        cs = findViewById(R.id.rb1);
        sha256 = findViewById(R.id.rb2);
        sha512 = findViewById(R.id.rb3);
        md5 = findViewById(R.id.rb4);
        choice = findViewById(R.id.choicegrp);
        start = findViewById(R.id.st);
        tv2 = findViewById(R.id.tv2);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choice.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    str = etv2.getText().toString();
                    if (str.length() == 0) {
                        etv2.requestFocus();
                        etv2.setError("Field can't be empty");
                    }
                    else {
                        if (choice.getCheckedRadioButtonId() == cs.getId()) {
                            StringBuffer result = new StringBuffer();
                            for (int i = 0; i < str.length(); i++) {
                                if (Character.isUpperCase(str.charAt(i))) {
                                    char ch = (char) (((int) str.charAt(i) + 3 - 65) % 26 + 65);
                                    result.append(ch);
                                } else {
                                    char ch = (char) (((int) str.charAt(i) + 3 - 97) % 26 + 97);
                                    result.append(ch);
                                }
                            }
                            Toast.makeText(getApplicationContext(), "Your text has been encrypted successfully", Toast.LENGTH_SHORT).show();
                            tv2.setText(result.toString());
                        }
                        if (choice.getCheckedRadioButtonId() == sha256.getId()) {
                            try {
                                MessageDigest md = MessageDigest.getInstance("SHA-256"); // Static getInstance method is called with hashing SHA-256
                                byte[] messageDigest = md.digest(str.getBytes()); // digest() method is called to calculate message digest of an input digest() return array of byte
                                BigInteger no = new BigInteger(1, messageDigest); // Convert byte array into signum representation
                                StringBuilder hashtext = new StringBuilder(no.toString(16)); // Convert message digest into hex value
                                while (hashtext.length() < 32) {
                                    hashtext.insert(0, '0');
                                }
                                tv2.setText(hashtext.toString());
                                Toast.makeText(getApplicationContext(), "Your text has been encrypted successfully", Toast.LENGTH_SHORT).show();
                            }
                            // For specifying wrong message digest algorithms
                            catch (NoSuchAlgorithmException e) {
                                throw new RuntimeException(e);
                            }

                        }
                        if (choice.getCheckedRadioButtonId() == sha512.getId()) {
                            try {
                                MessageDigest md = MessageDigest.getInstance("SHA-512"); // Static getInstance method is called with hashing MD5
                                byte[] messageDigest = md.digest(str.getBytes()); // digest() method is called to calculate message digest of an input digest() return array of byte
                                BigInteger no = new BigInteger(1, messageDigest); // Convert byte array into signum representation
                                hashtext = no.toString(16); // Convert message digest into hex value
                                while (hashtext.length() < 32) {
                                    hashtext = "0" + hashtext;
                                }
                                tv2.setText(hashtext);
                                Toast.makeText(getApplicationContext(), "Your text has been encrypted successfully", Toast.LENGTH_SHORT).show();
                            }
                            // For specifying wrong message digest algorithms
                            catch (NoSuchAlgorithmException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if (choice.getCheckedRadioButtonId() == md5.getId()) {
                            try {
                                MessageDigest md = MessageDigest.getInstance("MD5"); // Static getInstance method is called with hashing MD5
                                byte[] messageDigest = md.digest(str.getBytes()); // digest() method is called to calculate message digest of an input digest() return array of byte
                                BigInteger no = new BigInteger(1, messageDigest); // Convert byte array into signum representation
                                hashtext = no.toString(16); // Convert message digest into hex value
                                while (hashtext.length() < 32) {
                                    hashtext = "0" + hashtext;
                                }
                                tv2.setText(hashtext);
                                Toast.makeText(getApplicationContext(), "Your text has been encrypted successfully", Toast.LENGTH_SHORT).show();
                            }
                            // For specifying wrong message digest algorithms
                            catch (NoSuchAlgorithmException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        });
    }
}