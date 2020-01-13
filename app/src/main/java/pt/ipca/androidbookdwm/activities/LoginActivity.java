package pt.ipca.androidbookdwm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import pt.ipca.androidbookdwm.R;
import pt.ipca.androidbookdwm.dialogs.AlertConfig;
import pt.ipca.androidbookdwm.dialogs.Dialogs;
import pt.ipca.androidbookdwm.interfaces.DialogsResult;
import pt.ipca.androidbookdwm.utils.Constants;

public class LoginActivity extends AppCompatActivity {

    private static final String DUMMY_EMAIL = "user@user.pt";
    private static final String DUMMY_PASSWORD = "12345";

    private EditText email;
    private EditText password;
    private CheckBox cbRemember;
    private ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.setEmail);
        password = findViewById(R.id.setPassword);
        cbRemember = findViewById(R.id.checkBox);
        mainLayout = findViewById(R.id.mainLayout);

        getMyPreferences();
    }

    private SharedPreferences getMyPreferences() {
        SharedPreferences prefs = getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE); //parametros: nome do ficheiro, modo de ficheiro (publico ou privado)

        String Email = prefs.getString(Constants.EMAIL, null);
        String Password = prefs.getString(Constants.PASSWORD, null);
        boolean Remember = prefs.getBoolean(Constants.REMEMBER, false);

        if (email != null && password != null) {
            email.setText(Email);
            password.setText(Password);
        }
        cbRemember.setChecked(Remember);

        return prefs;
    }

    public void onLoginClick(View view) {

        if (!email.getText().toString().equals(DUMMY_EMAIL))
        {
            email.setError(getString(R.string.invalid_email));
            email.requestFocus();
            return;
        }

        if (!password.getText().toString().equals(DUMMY_PASSWORD))
        {
            password.setError(getString(R.string.invalid_password));
            password.requestFocus();
            return;
        }

        //Toast.makeText(getApplicationContext(), "SUCCESS" , Toast.LENGTH_SHORT).show();
        Snackbar.make(mainLayout, "SUCCESS", Snackbar.LENGTH_LONG).show(); //Parametros: onde vai ser chamado, o text, tempo

        SharedPreferences prefs = getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE); //parametros: nome do ficheiro, modo de ficheiro (publico ou privado)
        SharedPreferences.Editor editor = prefs.edit(); //sempre que se quiser alterar alguma coisa, funciona tipo ligação BD.

        //Se tiver clicado para relembra dados de login
        if (cbRemember.isChecked()) {
            editor.putString(Constants.EMAIL, email.getText().toString());
            editor.putString(Constants.PASSWORD, password.getText().toString());
        } else {
            editor.remove(Constants.EMAIL);
            editor.remove(Constants.PASSWORD);
        }

        editor.putBoolean(Constants.REMEMBER, cbRemember.isChecked()); //put"tipoDeDados", Parametros: Key, valor

        editor.apply(); //aplica todas as aplicações

        Intent intent = new Intent(getApplicationContext(), MainActivity.class); //serve para chamar qualquer activity ou app... passamos this e o objeto a passar.
        startActivity(intent);
    }

    public void onForgotClick(View view) {

        AlertConfig alertConfig = new AlertConfig(getString(R.string.forgot_password_title), getString(R.string.forgot_password_message), getString(R.string.ok));

        Dialogs.showAlertDialog(this, alertConfig, new DialogsResult() {
            @Override
            public void onResult(boolean ok) {

            }
        });
    }

    public void onCreatUserClick(View view) {
        Intent intent = new Intent(getApplicationContext(), CreateProduct.class); //serve para chamar qualquer activity ou app... passamos this e o objeto a passar.
        startActivity(intent);
    }
}