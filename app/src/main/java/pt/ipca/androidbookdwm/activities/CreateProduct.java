package pt.ipca.androidbookdwm.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;


import pt.ipca.androidbookdwm.R;

public class CreateUser extends AppCompatActivity {


    private Toolbar toolbar;
    private EditText firstName, lastName, email, password, confirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        toolbar = findViewById(R.id.toolbar);
        setUpToolbar();
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
    }

    //PARA RECEBER QUALQUER ITEM DA TOOLBAR "onOptionsItemSelected"
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //finish(); elemina o ecrã, volta a tras.
                onBackPressed(); //função já pre-definida que serve para voltar a tras.
                return true;
            default:
                    return super.onOptionsItemSelected(item);
        }

    }

    void setUpToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    public void onCreateUserClick(View view) {


    }
}
