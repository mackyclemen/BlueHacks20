package tech.codeverse.philcu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Activity8 extends AppCompatActivity
{

    ImageView img4;

    Intent iimg4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_8);

        img4 = findViewById(R.id.imageView4);

        iimg4 = new Intent(Activity8.this, Sining_Activity4.class);

        img4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(iimg4);
            }
        });
    }
}
