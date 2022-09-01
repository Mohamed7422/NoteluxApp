package com.mhm.notelux;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public  abstract  class SwipeToDelete extends ItemTouchHelper.SimpleCallback {




    public SwipeToDelete(int dragDirs, int swipeDirs) {
        super(dragDirs,swipeDirs);


    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
