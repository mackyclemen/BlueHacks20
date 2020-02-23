package tech.codeverse.philcu;

import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

import java.util.Objects;

public class ForgotPWActivity extends AppCompatActivity {

    TextInputEditText email;
    TextInputLayout lEmail;
    FirebaseAuth mAuth;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_forgot_pw);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email = findViewById(R.id.emailReset);
        lEmail = findViewById(R.id.lEmailReset);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void reset(View view) {
        if(Objects.requireNonNull(email.getText()).toString().isEmpty()) {
            lEmail.setError("Please enter your Email address");
            return;
        }

        mAuth.sendPasswordResetEmail(email.getText().toString())
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        successCall();
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        failureCall(e.getMessage());
                    }
                });
    }

    void successCall() {
        Toast.makeText(this, "An email is sent containing instructions for resetting your password.", Toast.LENGTH_LONG).show();
        finish();
    }

    void failureCall(String message) {
        lEmail.setError(message);
    }
}
