package com.example.loginbasic;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    Button btn_dn, btn_huy;
//    EditText edt_name, edtMK;
//    CheckBox chk_save;
//
//    public static String MName = "account.xml";
//    SharedPreferences preferences;
//    public static final int MODE = Activity.MODE_PRIVATE;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

//        btn_dn = findViewById(R.id.button);
//        btn_huy = findViewById(R.id.button2);
//        edt_name = findViewById(R.id.edt_name);
//        edtMK = findViewById(R.id.edt_pass);
//        chk_save = findViewById(R.id.chk_save);
//
//        //
//        preferences = getSharedPreferences(MName, MODE);
//        readAccount();
//        btn_dn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                saveAccount();
//            }
//        });
//
//
//    }
//
//    private void saveAccount(){
//        // lấy ra đối tượng editor để lứu
//        if(chk_save.isChecked()){
//            SharedPreferences.Editor editor = preferences.edit();
//            // put data in file xml
//            editor.putBoolean("isChecked", chk_save.isChecked());
//            editor.putString("userName", edt_name.getText().toString());
//            editor.putString("userPassword", edtMK.getText().toString());
//            // hoan tat viec ghi du lieu
//            editor.commit();
//        }
//    }
//
//    private void readAccount(){
//        Boolean isSave = preferences.getBoolean("isChecked", false);
//        if(isSave){
//            // read data
//            String name = preferences.getString("userName", null);
//            String pass = preferences.getString("userPassword", null);
//            edt_name.setText(name);
//            edtMK.setText(pass);
//        }
//    }


    private static final int REQUEST_CODE_INSERT_NOTE = 1; // Định nghĩa mã yêu cầu
    private static final int REQUEST_CODE_UPDATE_NOTE = 2;
    RecyclerView rcvlistNote;
    FloatingActionButton btn_add;

    NoteAdapter adapter ;
    List<Note> listNotes = new ArrayList<>();
    DBManager dbManager;
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

        rcvlistNote = findViewById(R.id.rcv_listNote);
        btn_add = findViewById(R.id.buton_add);

        dbManager = new DBManager(MainActivity.this);
        dbManager.open();
        listNotes = dbManager.getAllNote();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvlistNote.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvlistNote.addItemDecoration(itemDecoration);

        adapter = new NoteAdapter(listNotes, new IClickItemNote() {
            @Override
            public void onClickNote(Note note) {
                Intent intent = new Intent(MainActivity.this, UpdateNote.class);
                intent.putExtra("note", note); // Truyền đối tượng ghi chú qua intent
                startActivityForResult(intent, REQUEST_CODE_UPDATE_NOTE);
                System.out.println("têtttetete");
            }
        });
        rcvlistNote.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InsertNote.class);
                startActivityForResult(intent, REQUEST_CODE_INSERT_NOTE); // Gọi startActivityForResult
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_INSERT_NOTE && resultCode == RESULT_OK && data != null) {
            Note newNote = (Note) data.getSerializableExtra("newnote");
            Log.e( "main", "" + newNote.getTitle() );
            Log.e( "main", "" + newNote.getCreateDate() );


            listNotes.add(newNote);
            for (Note note : listNotes) {
                Log.d("Note", "Title: " + note.getTitle());
                Log.d("Note", "Date: " + note.getCreateDate());
                Log.d("Note", "Content: " + note.getContent());
            }

            adapter.notifyDataSetChanged();
        }


        if (requestCode == REQUEST_CODE_UPDATE_NOTE && resultCode == RESULT_OK && data != null) {
            Note updatedNote = (Note) data.getSerializableExtra("updatedNote");


            for (int i = 0; i < listNotes.size(); i++) {
                if (listNotes.get(i).getTd() == updatedNote.getTd()) {
                    listNotes.set(i, updatedNote);
                    break;
                }
            }

            adapter.notifyDataSetChanged();
        }
    }
}