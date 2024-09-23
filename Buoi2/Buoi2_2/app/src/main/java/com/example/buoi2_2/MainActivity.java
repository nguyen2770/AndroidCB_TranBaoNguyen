package com.example.buoi2_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        img = findViewById(R.id.imageView);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randomNum = (int)(Math.random() * 6) + 1;
                switch (randomNum){
                    case 1: img.setImageResource(R.drawable.xs_one);break;
                    case 2: img.setImageResource(R.drawable.xs_two);break;
                    case 3: img.setImageResource(R.drawable.xs_three);break;
                    case 4: img.setImageResource(R.drawable.xs_four);break;
                    case 5: img.setImageResource(R.drawable.xs_five);break;
                    case 6: img.setImageResource(R.drawable.xs_six);break;
                }
            }
        });
    }

}