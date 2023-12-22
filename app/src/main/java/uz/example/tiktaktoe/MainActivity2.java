package uz.example.tiktaktoe;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    TextView score_total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        score_total = findViewById(R.id.score_total);

        Intent i = getIntent();
        String r_t = i.getStringExtra("result_total");

        Toast.makeText(getApplicationContext(), "O'yin natijalar",
                Toast.LENGTH_LONG).show();
        score_total.setText(r_t);
    }

}