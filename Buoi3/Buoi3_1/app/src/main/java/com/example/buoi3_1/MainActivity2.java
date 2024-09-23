package com.example.buoi3_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    ImageView imgPhone;
    TextView txt_phone1, txt_phone2;
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

        imgPhone = findViewById(R.id.img_phone);
        txt_phone1 = findViewById(R.id.txt_phone1);
        txt_phone2 = findViewById(R.id.txt_phone2);

    }

    public void showPhoneNumberDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn số điện thoại");

        // Tạo danh sách các số điện thoại
        String[] phoneNumbers = {"123-456-7890", "098-765-4321"};

        // Tạo sự kiện cho mỗi mục trong danh sách
        builder.setItems(phoneNumbers, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Truyền số điện thoại đã chọn vào Intent
                String selectedPhoneNumber = phoneNumbers[which];
                Intent intent = new Intent(MainActivity2.this, Call);
                intent.putExtra("PHONE_NUMBER", selectedPhoneNumber);
                startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
}