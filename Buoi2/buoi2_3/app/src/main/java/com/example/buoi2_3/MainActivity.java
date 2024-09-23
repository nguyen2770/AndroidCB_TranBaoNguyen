package com.example.buoi2_3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edt_sdt;
    Button btn_sms, btn_call;
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

        edt_sdt = findViewById(R.id.edt_sdt);
        btn_call = findViewById(R.id.btn_phone);
        btn_sms = findViewById(R.id.btn_SMS);

        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!check_sdt(edt_sdt.getText().toString())){
                    Toast.makeText(MainActivity.this, "Số điện thoại không hợp lệ", Toast.LENGTH_LONG).show();
                }else {
                    Intent intent1 = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + edt_sdt.getText().toString()));

                    // yêu cầu accept quyền truy cập
                    if (ActivityCompat.checkSelfPermission (MainActivity.this,
                            Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions (MainActivity.this, new
                                String[]{Manifest.permission.SEND_SMS},1);
                        return;
                    }

                    startActivity(intent1);

                }
            }
        });

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!check_sdt(edt_sdt.getText().toString())){
                    Toast.makeText(MainActivity.this, "Số điện thoại không hợp lệ", Toast.LENGTH_LONG).show();
                }else {
                    Intent intent2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + edt_sdt.getText().toString()));

                    // yêu cầu accept quyền truy cập
                    if (ActivityCompat.checkSelfPermission (MainActivity.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions (MainActivity.this, new
                                String[]{Manifest.permission.CALL_PHONE},1);
                        return;
                    }

                    startActivity(intent2);

                }
            }
        });
    }

    private static boolean check_sdt(String s){
        String regex = "^0\\d{9,10}$";
        return s.matches(regex);
    }
}