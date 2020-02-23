package tech.codeverse.philcu;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    TextInputEditText fName, lName, email, pass, passConf;
    TextInputLayout lFName, lLName, lEmail, lPass, lPassConf;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_registration);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fName = findViewById(R.id.firstIn);
        lName = findViewById(R.id.lastIn);
        email = findViewById(R.id.emailIn);
        pass = findViewById(R.id.passIn);
        passConf = findViewById(R.id.passConf);

        lFName = findViewById(R.id.lFirstIn);
        lLName = findViewById(R.id.lLastIn);
        lEmail = findViewById(R.id.lEmailIn);
        lPass = findViewById(R.id.lPassIn);
        lPassConf = findViewById(R.id.lPassConf);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void register(View view) {

        view.setEnabled(false);
        boolean retCode = false;

        if(Objects.requireNonNull(fName.getText()).toString().isEmpty()) {
            lFName.setError("Field required");
            retCode = true;
        };
        if(Objects.requireNonNull(lName.getText()).toString().isEmpty()) {
            lLName.setError("Field required");
            retCode = true;
        };
        if(Objects.requireNonNull(email.getText()).toString().isEmpty()) {
            lEmail.setError("Field required");
            retCode = true;
        };
        if(Objects.requireNonNull(pass.getText()).toString().isEmpty()) {
            lPass.setError("Field required");
            retCode = true;
        };
        if(Objects.requireNonNull(passConf.getText()).toString().isEmpty()) {
            lPassConf.setError("Field required");
            retCode = true;
        };

        if(!pass.getText().toString().equals(passConf.getText().toString())) {
            lPassConf.setError("Passwords don't match");
        }

        if(retCode) {
            view.setEnabled(true);
        } else {

            mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                    .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            // mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString());
                            success();
                        }
                    })
                    .addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

        }

    }

    void success() {
        FirebaseUser user = Objects.requireNonNull(mAuth.getCurrentUser());
        UserProfileChangeRequest.Builder b = new UserProfileChangeRequest.Builder();
        b.setDisplayName(fName.getText().toString() + " " + lName.getText().toString());
        user.updateProfile(b.build());

        Toast.makeText(this, "You have signed up successfully, " + user.getDisplayName() + "!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, LaunchActivity.class);
        startActivity(i);
        finish();
    }
}
