package com.example.buoi2;

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

    EditText edtA, edtB;
    TextView txtKQ;
    Button btn_Tong, btn_hieu, btn_nhan, btn_chia, btn_ChiaDu;
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

        edtA = findViewById(R.id.edt_A);
        edtB = findViewById(R.id.edt_B);
        txtKQ  = findViewById(R.id.txt_kq);
        btn_Tong = findViewById(R.id.btn_Cong);

        btn_Tong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int A,B;
                A = Integer.parseInt(edtA.getText().toString());
                B = Integer.parseInt(edtB.getText().toString());
                int tong = A + B;
                txtKQ.setText(tong +" ");
            }
        });

        btn_hieu = findViewById(R.id.btn_Tru);
        btn_hieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int A,B;
                A = Integer.parseInt(edtA.getText().toString());
                B = Integer.parseInt(edtB.getText().toString());
                int tong = A - B;
                txtKQ.setText(tong +" ");
            }
        });

        btn_nhan = findViewById(R.id.btnNhan);
        btn_nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int A,B;
                A = Integer.parseInt(edtA.getText().toString());
                B = Integer.parseInt(edtB.getText().toString());
                int tong = A * B;
                txtKQ.setText(tong +" ");
            }
        });

        btn_chia = findViewById(R.id.btn_Chia);
        btn_chia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int A,B;
                A = Integer.parseInt(edtA.getText().toString());
                B = Integer.parseInt(edtB.getText().toString());
                int tong = A / B;
                txtKQ.setText(tong +" ");
            }
        });

        btn_ChiaDu = findViewById(R.id.btn_ChiaDu);
        btn_ChiaDu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int A,B;
                A = Integer.parseInt(edtA.getText().toString());
                B = Integer.parseInt(edtB.getText().toString());
                int tong = A % B;
                txtKQ.setText(tong +" ");
            }
        });
    }



}