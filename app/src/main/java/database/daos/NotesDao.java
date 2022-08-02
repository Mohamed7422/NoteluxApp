package database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import database.model.Note;

@Dao
public interface NotesDao {

    @Insert
    void addNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Update
    void updateNote(Note note);

    @Query ("select * from note where id=:id")
    Note searchNoteById(int id);

    @Query ("select * from note")
    List<Note>getAllNotes();

}
