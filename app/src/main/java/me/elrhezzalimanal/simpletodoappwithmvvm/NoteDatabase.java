package me.elrhezzalimanal.simpletodoappwithmvvm;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Room subclasses this abstract class
@Database(entities = {Note.class}, version = 1 )
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance; // to make this class a Singleton

    //added this method so that we will be able to access methods that are in the NoteDao interface
    public abstract NoteDao noteDao();

    //let's make synchronized so that only one thread can access this method at a time
    // so that we don't create accidentally 2 instances of this database
    // when 2 threads try to access this database at the same time
    public static synchronized NoteDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
