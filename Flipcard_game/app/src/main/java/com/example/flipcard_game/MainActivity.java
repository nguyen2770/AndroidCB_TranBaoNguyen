package com.example.flipcard_game;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private FiipCardAdapter adapter;
    private List<Integer> imageList;

    private int firstSelected = -1;
    private int secondSelected = -1;
    private boolean isChecking = false;
    private int tmp = 0;

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

        gridView = findViewById(R.id.gridView);
        imageList = Arrays.asList(
                R.drawable.caterpie, R.drawable.charmander, R.drawable.eevee, R.drawable.pikachu,
                R.drawable.game, R.drawable.meowth, R.drawable.venonat, R.drawable.avatar,
                R.drawable.caterpie, R.drawable.charmander, R.drawable.eevee, R.drawable.pikachu,
                R.drawable.game, R.drawable.meowth, R.drawable.venonat, R.drawable.avatar
        );
        // Shuffle danh sách hình ảnh để ngẫu nhiên vị trí các thẻ
        Collections.shuffle(imageList);

        // Khởi tạo adapter
        adapter = new FiipCardAdapter(this, imageList);
        gridView.setAdapter(adapter);

        // Xử lý sự kiện khi người dùng bấm vào thẻ
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isChecking || adapter.isFlipped(position) || adapter.isMatched(position)) {
                    return;  // Nếu đang kiểm tra hoặc thẻ đã lật/khớp, không làm gì
                }

                adapter.flipCard(position);

                if (firstSelected == -1) {
                    firstSelected = position;
                } else {
                    secondSelected = position;
                    isChecking = true;

                    // Kiểm tra khớp cặp
                    if (imageList.get(firstSelected).equals(imageList.get(secondSelected))) {
                        // Nếu khớp, đánh dấu là đã khớp

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                adapter.setMatched(firstSelected, secondSelected);
                                tmp++;
                                Log.e("onItemClick: ", tmp + "");
                                resetSelection();
                                // Kiểm tra xem đã hoàn thành game chưa
                                if (tmp == 8) {
                                    Toast.makeText(MainActivity.this, "You WIN", Toast.LENGTH_LONG).show();
                                }


                            }
                        }, 500);  // 1 giây


                    } else {
                        // Nếu không khớp, lật lại sau một khoảng thời gian
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                adapter.resetFlips(firstSelected, secondSelected);
                                resetSelection();
                            }
                        }, 1000);  // 1 giây
                    }
                }
            }

        });


    }

    private void resetSelection() {
        firstSelected = -1;
        secondSelected = -1;
        isChecking = false;
    }


}