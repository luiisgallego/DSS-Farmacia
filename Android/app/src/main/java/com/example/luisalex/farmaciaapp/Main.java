package com.example.luisalex.farmaciaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    private Button botonLogIn;
    private Button botonSignUp;
    private EditText textNick;
    private EditText textPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        botonLogIn = findViewById(R.id.botonLogIn);
        botonSignUp = findViewById(R.id.botonSignUp);

        textNick = findViewById(R.id.nickinput);
        textPass = findViewById(R.id.passinput);

        final DBHelper dbHelper = new DBHelper(this,"Base Datos",null,1);

        botonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyValidation()) {
                    dbHelper.addUsuario(new UsuarioInicio(textNick.getText().toString(), textPass.getText().toString()));
                    Toast.makeText(Main.this, "UsuarioInicio añadido", Toast.LENGTH_SHORT).show();
                    textNick.setText("");
                    textPass.setText("");
                }else{
                    Toast.makeText(Main.this, "Campos Vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        botonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyValidation()) {
                    UsuarioInicio user = dbHelper.obtenerUsuario(textNick.getText().toString(), textPass.getText().toString());
                    if (user != null) {
                        Bundle mBundle = new Bundle();
                        mBundle.putString("UsuarioInicio", user.getNick());
                        Intent intent = new Intent(Main.this, ActividadUsuario.class);
                        intent.putExtras(mBundle);
                        startActivity(intent);
                        Toast.makeText(Main.this, "Bienvenido " + user.getNick(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Main.this, "UsuarioInicio inexistente", Toast.LENGTH_SHORT).show();
                        textPass.setText("");
                    }
                }else{
                    Toast.makeText(Main.this, "Campos vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean emptyValidation() {
        if (TextUtils.isEmpty(textNick.getText().toString()) || TextUtils.isEmpty(textPass.getText().toString())) {
            return true;
        }else {
            return false;
        }
    }
}
