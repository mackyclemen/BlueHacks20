package tech.codeverse.philcu;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout lEmail, lPassword;
    TextInputEditText email, password;
    FirebaseAuth mAuth;
    boolean isLoggedIn = false;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email = findViewById(R.id.login_email_input);
        password = findViewById(R.id.login_password_input);

        lEmail = findViewById(R.id.login_email_box);
        lPassword = findViewById(R.id.login_password_box);

    }

    public void loggedInCall() {
        Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, logoutActivity.class);
        startActivity(i);
        finish();
    }

    public void failedLogInCall(String message, View v) {
        lPassword.setError(message);
        Toast.makeText(this, "Something happened while logging you in", Toast.LENGTH_SHORT).show();
        v.setEnabled(true);
    }

    public void onClickCallback(final View view) {

        Intent i;

        switch(view.getId()) {
            case R.id.login_only_btn:
                view.setEnabled(false);

                boolean retCode = false;

                if(Objects.requireNonNull(email.getText()).toString().isEmpty()) {
                    lEmail.setError("Enter an email address");
                    retCode = true;
                }

                if(Objects.requireNonNull(password.getText()).toString().isEmpty()) {
                    lPassword.setError("Enter password");
                    retCode = true;
                }

                if(retCode) {
                    view.setEnabled(true);
                    return;
                }

                String emailStr = email.getText().toString();
                String passStr = password.getText().toString();

                mAuth.signInWithEmailAndPassword(emailStr, passStr)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    loggedInCall();
                                }
                            }
                        })
                        .addOnFailureListener(this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                failedLogInCall(e.getMessage(), view);
                            }
                        });

                break;

            case R.id.sign_up_btn:
                i = new Intent(this, RegistrationActivity.class);
                startActivity(i);
                break;

            case R.id.forgotpass_btn:
                i = new Intent(this, ForgotPWActivity.class);
                startActivity(i);
                break;
        }
    }
}
