package com.example.chuyendoinhietdo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    // tạo các biến môi trường
    EditText edt_C, edt_F;
    Button btn_CVtoC, btn_CVtoF, btn_Clear;
    DecimalFormat dcf = new DecimalFormat("0.00");

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



        // lấy ra các id và gán cho các biến môi trường
        edt_C = findViewById(R.id.edt_C);
        edt_F = findViewById(R.id.edt_F);
        btn_CVtoC = findViewById(R.id.btn_CVtoC);
        btn_CVtoF = findViewById(R.id.btn_CVtoF);
        btn_Clear = findViewById(R.id.btn_clear);

        // xử lý các sự kiện

        btn_CVtoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edt_F.getText().toString().isEmpty()){

                    float a = Float.parseFloat(edt_F.getText().toString());
                    float b = (float) ((a - 32.0) * 0.555);
                    edt_C.setText(dcf.format(b)+"");
                }else{
                    edt_C.setText("vui lòng nhập đầy đủ thông tin");
                }


            }
        });

        btn_CVtoF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edt_C.getText().toString().isEmpty()){
                    float a = Float.parseFloat(edt_C.getText().toString());
                    float b = (float) (a*1.8 + 32.0);
                    edt_F.setText(dcf.format(b)+"");
                }else{
                    edt_F.setText("vui lòng nhập đầy đủ thông tin");
                }
            }
        });

        btn_Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_C.setText("");
                edt_F.setText("");
            }
        });

    }
}