package tech.codeverse.philcu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    public void onBackPressed() {
        if (mAuth.getCurrentUser() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Logout")
                    .setMessage("Do you want to logout?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mAuth.signOut();
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
    }

    public void imgViewClickHandler(View view) {

        Intent i = null;

        switch(view.getId()) {
            case R.id.imageView1:
                i = new Intent(this, Activity1.class);
                break;

            case R.id.imageView2:
                i = new Intent(this, Activity2.class);
                break;

            case R.id.imageView3:
                i = new Intent(this, Activity3.class);
                break;

            case R.id.imageView4:
                i = new Intent(this, Activity4.class);
                break;

            case R.id.imageView5:
                i = new Intent(this, Activity5.class);
                break;

            case R.id.imageView6:
                i = new Intent(this, Activity6.class);
                break;

            case R.id.imageView7:
                i = new Intent(this, Activity7.class);
                break;

            case R.id.imageView8:
                i = new Intent(this, Activity8.class);
                break;

            default:
                Toast.makeText(this,
                        "onClick not implemented on this view",
                        Toast.LENGTH_LONG)
                        .show();
        }

        startActivity(i);
    }
}
