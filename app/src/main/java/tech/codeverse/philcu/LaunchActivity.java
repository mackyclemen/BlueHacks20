package tech.codeverse.philcu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LaunchActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null) {
            Intent i = new Intent(this, logoutActivity.class);
            startActivity(i);
            finish();
        }

        setContentView(R.layout.activity_launch);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.btn_attrib:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Attribution")
                        .setMessage("Crizaldy Diverson's Long exposure photography of waterfalls (https://www.pexels.com/photo/long-exposure-photography-of-waterfalls-2407265/)")
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickCallback(View view) {
        
        Intent i;
        
        switch(view.getId()) {
            case R.id.login_btn:
                i = new Intent(this, LoginActivity.class);
                startActivity(i);
                break;
            default:
                Toast.makeText(this, "Buttons not implemented", Toast.LENGTH_SHORT).show();
        }
    }
}
