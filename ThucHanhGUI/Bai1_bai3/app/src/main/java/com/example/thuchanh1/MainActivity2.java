package com.example.thuchanh1;

import android.media.MediaTimestamp;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity2 extends AppCompatActivity {
    EditText edt1,det2,edt3,edt4;
    Button btn7;
    String S = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edt1 = findViewById(R.id.editTextText);
        det2 = findViewById(R.id.editTextText2);
        edt3 = findViewById(R.id.editTextText3);
        edt4 = findViewById(R.id.editTextText4);
        btn7 = findViewById(R.id.button7);

        btn7.setOnClickListener(view->btnTinhBMI());

    }

    private void btnTinhBMI(){
        float cn = Float.parseFloat(edt1.getText().toString());
        float cc = Float.parseFloat(det2.getText().toString());
        float bmi = (float) (cn/Math.pow(cc,2));
        if(bmi < 18){
            S = "Bạn gầy";
        } else if (bmi < 24.9) {
            S = "Bạn binh thường";
        } else if (bmi < 29.9) {
            S = "Bạn béo phì độ 1";
        } else if (bmi < 34.9) {
            S = "Bạn béo phì độ 2";
        }else{
            S = "Bạn beos phì độ 3";
        }
        DecimalFormat dcf = new DecimalFormat("#.0");
        edt3.setText(dcf.format(bmi));
        edt4.setText(S);


    }


}