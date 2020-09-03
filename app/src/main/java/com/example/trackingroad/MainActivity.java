package com.example.trackingroad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText userText,passwordText;
    Button loginButton,clearButton,forgetButton,signUpButton;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userText=(EditText)findViewById(R.id.userTextId);
        passwordText=(EditText)findViewById(R.id.passwordTextId);

        loginButton=(Button)findViewById(R.id.loginButtonId);
        clearButton=(Button)findViewById(R.id.clearButtonId);
        forgetButton=(Button)findViewById(R.id.forgetPasswordId);
        signUpButton=(Button)findViewById(R.id.signUpId);

        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase= databaseHelper.getWritableDatabase();

        loginButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        forgetButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.settingId)
        {
            Intent settingIntent=new Intent(getApplicationContext(),Setting.class);
            startActivity(settingIntent);
        }
        if(item.getItemId()==R.id.shareId)
        {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");

            String subject="c programming app";
            String body="This app is very useful to learn c programming";

            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_SUBJECT,body);

            startActivity(Intent.createChooser(intent,"share with"));

        }

        if(item.getItemId()==R.id.feedbackId)
        {
            Intent intent=new Intent(getApplicationContext(),Feedback.class);
            startActivity(intent);
        }

        if(item.getItemId()==R.id.aboutUsId)
        {
            Intent aboutIntent=new Intent(getApplicationContext(),About.class);
            startActivity(aboutIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        String userName=userText.getText().toString();
        String password=passwordText.getText().toString();

        if(v.getId()==R.id.loginButtonId)
        {
            Boolean result =databaseHelper.findPassword(userName,password);

            if(result==true)
            {
                Intent login=new Intent(MainActivity.this,Login.class);
                startActivity(login);
            }
            else {
                Toast.makeText(getApplicationContext(),"Username and Password didn't match",Toast.LENGTH_SHORT).show();
            }

        }
        if(v.getId()==R.id.clearButtonId)
        {
            userText.setText("");
            passwordText.setText("");
        }
        if(v.getId()==R.id.forgetPasswordId)
        {
            Intent forget=new Intent(MainActivity.this,ForgetPassword.class);
            startActivity(forget);
        }
        if(v.getId()==R.id.signUpId)
        {
            Intent signUp=new Intent(MainActivity.this,CreateAccount.class);
            startActivity(signUp);
        }
    }
}