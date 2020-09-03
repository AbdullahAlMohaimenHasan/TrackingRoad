package com.example.trackingroad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener{

    EditText fullNameText,userNameText,emailText,phoneNumberText,signPasswordText;
    Button signUp,clear,signIn;

    UserDetails userDetails;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        fullNameText=(EditText)findViewById(R.id.fullNameTextId);
        userNameText=(EditText)findViewById(R.id.userNameTextId);
        emailText=(EditText)findViewById(R.id.emailTextId);
        phoneNumberText=(EditText)findViewById(R.id.phoneTextId);
        signPasswordText=(EditText)findViewById(R.id.passwordId);

        signUp=(Button)findViewById(R.id.newSignUptId);
        clear=(Button)findViewById(R.id.signUpClearId);
        signIn=(Button)findViewById(R.id.signInId);

        databaseHelper=new DatabaseHelper(this);
        userDetails=new UserDetails();

        signIn.setOnClickListener(this);
        clear.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {


        if (view.getId() == R.id.signInId)
        {
            Intent signIn=new Intent(CreateAccount.this,MainActivity.class);
            startActivity(signIn);
        }
        if(view.getId()==R.id.signUpClearId)
        {
            fullNameText.setText("");
            userNameText.setText("");
            emailText.setText("");
            phoneNumberText.setText("");
            signPasswordText.setText("");
        }
        if(view.getId()==R.id.newSignUptId)
        {
            String name=fullNameText.getText().toString();
            String username=userNameText.getText().toString();
            String email=emailText.getText().toString();
            String phoneNumber=phoneNumberText.getText().toString();
            String password=signPasswordText.getText().toString();

            userDetails.setName(name);
            userDetails.setUsername(username);
            userDetails.setEmail(email);
            userDetails.setPhoneNumber(phoneNumber);
            userDetails.setPassword(password);

            long rowId=databaseHelper.insertData(userDetails);

            if (rowId>0)
            {
                Toast.makeText(getApplicationContext(),"Row "+rowId+" is successfully inserted",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Row insertion failed",Toast.LENGTH_SHORT).show();
            }
        }
    }
}