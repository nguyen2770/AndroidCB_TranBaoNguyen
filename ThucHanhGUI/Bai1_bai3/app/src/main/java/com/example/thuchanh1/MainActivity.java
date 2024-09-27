package com.example.thuchanh1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView txtKQ;
    EditText txtA, txtB;
    Button btnTong, BtnHieu, btnTich, btnThuong, btnUC, btnThoat;
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
        txtA = findViewById(R.id.edtA);
        txtB = findViewById(R.id.edt_B);
        txtKQ = findViewById(R.id.txtKQ);
        btnTong = findViewById(R.id.button);
        BtnHieu = findViewById(R.id.button2);
        btnTich = findViewById(R.id.button3);
        btnThuong = findViewById(R.id.button4);
        btnUC = findViewById(R.id.button5);
        btnThoat = findViewById(R.id.button6);

        float a = Float.parseFloat(txtA.getText().toString());
        float b = Float.parseFloat(txtB.getText().toString());


        btnTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float tmp = a + b;
                txtKQ.setText(tmp + "");
            }
        });
        BtnHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float tmp = a + b;
                txtKQ.setText(tmp + "");
            }
        });

        btnTich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float tmp = a * b;
                txtKQ.setText(tmp + "");
            }
        });

        btnThuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float tmp = a / b;
                txtKQ.setText(tmp + "");
            }
        });
        btnUC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float tmp1 = a, tmp2 =b;
                while(tmp1 == tmp2){
                    if(tmp1 > tmp2){
                        tmp1 = tmp1 - tmp2;
                    }else if(tmp2 > tmp1){
                        tmp2 = tmp2 - tmp1;
                    }
                }
                txtKQ.setText("UCLN: " + tmp1);
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}