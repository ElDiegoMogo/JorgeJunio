package com.example.appperros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Registrarse extends AppCompatActivity {

    private FirebaseAuth mAuth;
private EditText correo;
private EditText contrasena;
private EditText contrasenaconfirmacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        mAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);
        contrasenaconfirmacion = findViewById(R.id.contrsenaConfirmacion)
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


    public void  registrarUsuario (View View){

        if(contrasena.getText().equals(contrasenaconfirmacion.getText())){  private void firebaseAuthWithGoogle(String
            String idToken;
            idToken) {
            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                            } else {
                                // If sign in fails, display a message to the user.
                                Snackbar.make(getApplicationContext(), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
        }

        }else{
            Toast.makeText(this."las contrasenas no coinciden",Toast.LENGTH_SHORT).show();
        }



}