package com.example.appperros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registrarse extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText correo;
    private EditText contrasena;
    private EditText contrasenaconfirmacion;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Button button= (Button)findViewById(R.id.registrarBtn);
    button.setOnClickListener(new View.OnClickListener()){
        public void onClick(View v){

        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        mAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);
        contrasenaconfirmacion = findViewById(R.id.contrsenaConfirmacion);
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


    public void  registrarUsuario (View View) {

        if (contrasena.getText().equals(contrasenaconfirmacion.getText())) {
            mAuth.createUserWithEmailAndPassword(correo.toString(), contrasena.toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Registrarse.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                            // ...
                        }
                    });

            EditText correoText = (EditText)findViewById(R.id.correo);
            EditText contrasenaText = (EditText)findViewById(R.id.contrasena);

            // Create a new user with a first and last name
            Map<String, Object> user = new HashMap<>();
            user.put("correo", correoText);
            user.put("contrasena", contrasenaText);

            // Add a new document with a generated ID
            db.collection("users")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //Log.w(TAG, "Error adding document", e);
                        }
                    });

        }
    }
}