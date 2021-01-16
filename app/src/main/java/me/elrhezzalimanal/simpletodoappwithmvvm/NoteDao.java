package me.elrhezzalimanal.simpletodoappwithmvvm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao // tells Room that this is a Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    // we want to delete all note at once, Room doesn't have an annotation for this so we use Query annotation and pass the query as a String
    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    //we need another method that returns all the Notes objects so that we can put them to our RecyclerView
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes(); //now by adding LiveData we can observe this object so soon as they're any changes in our note_table our Activity will be notified and Room takes care of the necessary stuff this LiveData object


}
