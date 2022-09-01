package com.mhm.notelux;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import database.MyDataBase;
import database.model.Note;

public class HomeActivity extends AppCompatActivity {

   RecyclerView recyclerView;
   RecyclerView.LayoutManager layoutManager;
   NotesRecyclerAdapter adapter;
    List<Note> noteList;
    Note deletedNote;
    String title;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.recycler);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initRecyclerView();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this,AddNoteActivity.class));

                Snackbar.make(v,"Replace with your own action",Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        noteList  = MyDataBase.getInstance(this)
                              .notesDao().getAllNotes();
        adapter.changelist(noteList);
    }

    private void initRecyclerView() {

        adapter = new NotesRecyclerAdapter(null);
        layoutManager =  new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //get item position before swipe
                int position = viewHolder.getAdapterPosition();
                //get the item of the position
                deletedNote = noteList.get(position);
                title = deletedNote.getTitle();

                //when item swiped
                //remove item from the list
                noteList.remove(position);
                //notify Adapter
                adapter.notifyItemRemoved(position);

                //make a snake bar to make an action of showing undo
                Snackbar.make(recyclerView,title+" is removed",Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        noteList.add(position,deletedNote);
                        adapter.notifyItemInserted(position);
                    }
                }).show();
            }
        }).attachToRecyclerView(recyclerView);
    }














}