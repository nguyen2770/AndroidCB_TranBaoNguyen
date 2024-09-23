package com.example.buoi3_1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layout;
    private HashMap<Integer,String> sb = new HashMap<>();
    private ImageView imgv;
    private TextView txt;
    private int[] ranbackg = new int[]{R.color.orange, R.color.red, R.color.white};

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
        sb.put(R.drawable.penguin,"Chim");
        sb.put(R.drawable.hamster,"chuột");
        sb.put(R.drawable.turtle,"rùa");
        txt = findViewById(R.id.txt_nameAns);
        imgv = findViewById(R.id.imgv_ans);
        layout = findViewById(R.id.main);

        Random random = new Random();
        Object[] keys = sb.keySet().toArray();
        int randomKey = (int) keys[random.nextInt(keys.length)];
        String randomValue = sb.get(randomKey);

        txt.setText(randomValue);
        imgv.setImageResource(randomKey);

        int randomBackground = ranbackg[random.nextInt(ranbackg.length)];
        layout.setBackgroundResource(randomBackground);

    }


}