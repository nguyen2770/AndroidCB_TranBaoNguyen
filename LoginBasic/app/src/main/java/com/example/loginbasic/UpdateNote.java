package com.example.loginbasic;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class UpdateNote extends AppCompatActivity {
    EditText edtTitle, edtContent;
    TextView txtCreateDate;
    Button btnUpdate, btnSelectDate;
    Note note;
    final Calendar calendar = Calendar.getInstance();
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtTitle = findViewById(R.id.edtTitle);
        edtContent = findViewById(R.id.edtContent);
        txtCreateDate = findViewById(R.id.tvCreateDate);
        btnUpdate = findViewById(R.id.update_btn);
        btnSelectDate = findViewById(R.id.selectDate_btn);

        // Nhận ghi chú từ Intent
        note = (Note) getIntent().getSerializableExtra("note");

        // Hiển thị dữ liệu của ghi chú để người dùng sửa
        if (note != null) {
            edtTitle.setText(note.getTitle());
            edtContent.setText(note.getContent());
            txtCreateDate.setText(note.getCreateDate());
        }


        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateNote.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                txtCreateDate.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtTitle.getText().toString();
                String content = edtContent.getText().toString();
                String date = txtCreateDate.getText().toString();


                dbManager = new DBManager(UpdateNote.this);
                dbManager.open();
                dbManager.updateNote(note.getTd(), title, date, content); // Gọi phương thức updateNote


                note.setTitle(title);
                note.setContent(content);
                note.setCreateDate(date);


                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedNote", note);
                setResult(RESULT_OK, resultIntent);


                finish();
            }
        });
    }
}