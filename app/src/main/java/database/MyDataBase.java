package database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import database.daos.NotesDao;
import database.model.Note;

@Database(entities = {Note.class},version = 1,exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {
    //singleton design pattern
    //this way is not thread saving
    private static volatile MyDataBase dataBase = null;
    private final static String DATABASE_NAME="notesDatabase";
    public abstract NotesDao notesDao();

    public static  MyDataBase getInstance(Context context ){
        if (dataBase==null) {
            //create
            //make synchronize to double checked locking
            synchronized (MyDataBase.class) {

                if (dataBase==null)
                dataBase = Room.databaseBuilder(context, MyDataBase.class, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return dataBase;
    }

}
