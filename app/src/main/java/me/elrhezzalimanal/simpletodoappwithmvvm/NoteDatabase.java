package me.elrhezzalimanal.simpletodoappwithmvvm;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                    .addCallback(roomCallBack) //attaches the callback to the db
                    .build();
        }
        return instance;
    }

    //lets populate our database as soon as we create it
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 2));
            noteDao.insert(new Note("Title 3", "Description 3", 3));
            return null;
        }
    }

}
