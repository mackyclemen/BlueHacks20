package tech.codeverse.philcu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Sining_Activity4 extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sining_4);
    }

    public void interesting(View view) {
        Intent i = new Intent(this, HabiHowToActivity.class);
        startActivity(i);
    }
}
