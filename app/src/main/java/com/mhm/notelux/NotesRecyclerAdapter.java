package com.mhm.notelux;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import database.model.Note;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> {


    List<Note> noteList;

    public NotesRecyclerAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.title.setText(note.getTitle());
        holder.time.setText(note.getTime());

    }

    public void removeItem(RecyclerView.ViewHolder viewHolder){
        noteList.remove(viewHolder.getAdapterPosition());
        notifyItemRemoved(viewHolder.getAdapterPosition());
    }



    @Override
    public int getItemCount() {
        if (noteList == null) return 0;
        return noteList.size();

    }

    public void changelist(List<Note> noteList){
        this.noteList = noteList;
        notifyDataSetChanged();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
        }

    }
    
    

}