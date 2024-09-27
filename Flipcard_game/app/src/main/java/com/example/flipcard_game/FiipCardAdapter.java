package com.example.flipcard_game;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

public class FiipCardAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> list;
    boolean[] flipped;
    boolean[] isMatched; // Đánh dấu các cặp đã khớp


    public FiipCardAdapter(Context context, List<Integer> imageList) {
        this.context = context;
        this.list = imageList;
        this.flipped = new boolean[imageList.size()];
        this.isMatched = new boolean[imageList.size()];
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) view;
        }

        if (isMatched[i]) {
            imageView.setVisibility(View.INVISIBLE);  // Ẩn các thẻ đã khớp
        } else if (flipped[i]) {
            imageView.setImageResource(list.get(i));  // Hiển thị ảnh nếu thẻ đã lật
        } else {
            imageView.setImageResource(R.drawable.pokeball);  // Ảnh mặc định nếu thẻ chưa lật
        }

        return imageView;
    }

    public void flipCard(int position) {
//        if (isMatched[position]) {  // Chỉ lật nếu thẻ chưa được khớp
        flipped[position] = !flipped[position];
        notifyDataSetChanged();
        // }
    }

    public boolean isFlipped(int position) {
        return flipped[position];
    }

    public boolean isMatched(int position) {
        return isMatched[position];
    }

    public void setMatched(int pos1, int pos2) {
        isMatched[pos1] = true;
        isMatched[pos2] = true;
        notifyDataSetChanged();
    }

    public void resetFlips(int pos1, int pos2) {
        flipped[pos1] = false;
        flipped[pos2] = false;
        notifyDataSetChanged();
    }

    public boolean allMatched() {
        for (boolean m : isMatched) {
            if (!m) return false;
        }
        return true;
    }

    public void setPlayAgain() {
        for (int i = 0; i < isMatched.length; i++) {
            isMatched[i] = false;  // Đặt tất cả thẻ về trạng thái chưa lật
        }
    }


}
