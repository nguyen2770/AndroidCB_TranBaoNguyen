package com.example.loginbasic;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class InsertNote extends AppCompatActivity {

    EditText edtTitle, edt_content;
    TextView txt_date;
    Button btn_save, btn_choseDate;
    Calendar calendar = Calendar.getInstance();
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insert_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edt_content = findViewById(R.id.edt_content);
        edtTitle = findViewById(R.id.edt_Name);
        txt_date = findViewById(R.id.txt_date);
        btn_save = findViewById(R.id.btn_save);
        btn_choseDate = findViewById(R.id.btn_choseDate);

        btn_choseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog pickerDialog = new DatePickerDialog(InsertNote.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                        txt_date.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                pickerDialog.show();
                dbManager = new DBManager(InsertNote.this);


            }

        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lưu ghi chú vào database
                Note note = new Note(edtTitle.getText().toString(),
                        txt_date.getText().toString(),
                        edt_content.getText().toString()); // Sửa tên biến
                DBManager dbManager = new DBManager(InsertNote.this);
                dbManager.open();
                dbManager.insertNote(note); // Lưu vào database

                Log.e( "insert", "" + edtTitle.getText().toString() );
                Log.e( "insert", "" + txt_date.getText().toString() );
                Log.e( "insert", "" + edt_content.getText().toString() );
                // Gửi dữ liệu ghi chú về MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("newnote", note);
                setResult(RESULT_OK, resultIntent); // Trả kết quả về MainActivity
                finish(); // Quay lại MainActivity
            }
        });

    }




}