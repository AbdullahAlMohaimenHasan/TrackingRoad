package com.example.trackingroad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener{

    EditText newPasswordText,confirmPasswordText,userEmailText;
    Button changePassword,cleartext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        //back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        newPasswordText=(EditText)findViewById(R.id.newPasswordTextId);
        confirmPasswordText=(EditText)findViewById(R.id.confirmPasswordTextId);
        changePassword=(Button)findViewById(R.id.changePasswordId);
        cleartext=(Button) findViewById(R.id.changeClearId);
        userEmailText=(EditText)findViewById(R.id.userEmailTextId);

        changePassword.setOnClickListener(this);
        cleartext.setOnClickListener(this);
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
    public void onClick(View v) {

        String newPassword=newPasswordText.getText().toString();
        String confirmPassword=confirmPasswordText.getText().toString();

        if(v.getId()==R.id.changePasswordId)
        {

        }
        if(v.getId()==R.id.changeClearId)
        {
            newPasswordText.setText("");
            confirmPasswordText.setText("");
        }
    }
}