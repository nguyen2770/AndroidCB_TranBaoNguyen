package com.example.loginbasic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.noteViewHoder> {

    List<Note> noteList;
    IClickItemNote iClickItemNote;
    public NoteAdapter(List<Note> noteList, IClickItemNote iClickItemNote)   {
        this.noteList = noteList;
        this.iClickItemNote = iClickItemNote;
    }

    @NonNull
    @Override
    public NoteAdapter.noteViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_note,parent,false);

        return new noteViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.noteViewHoder holder, int position) {
        Note note = noteList.get(position);

        if(note == null){
            return;
        }
        holder.txtTitle.setText(note.title);
        holder.txtDate.setText(note.createDate);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemNote.onClickNote(note);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(noteList != null){
            return noteList.size();
        }
        return 0;
    }

    public class noteViewHoder extends RecyclerView.ViewHolder {

        private TextView txtTitle, txtDate;
        private LinearLayout layout;
        public noteViewHoder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.textView);
            txtDate = itemView.findViewById(R.id.textView2);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
