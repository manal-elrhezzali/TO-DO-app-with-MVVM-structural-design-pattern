package me.elrhezzalimanal.simpletodoappwithmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);//set it to true if you know that the recyclerView's size won't change ,it will make it more efficient

        // final NoteAdapter adapter = new NoteAdapter();
        NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())
        ).get(NoteViewModel.class);


        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            // this method is called every time the data in our LiveData changes
            // & if the View is in the foreground
            // so the activity receives an updated list of notes
            // whenever the data in the corresponding SQLite table changes
            // if we change the configuration for exp and the activity is destroyed this won't hold
            // a reference to our activity anymore
            @Override
            public void onChanged(List<Note> notes) {
                //update RecyclerView
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
                adapter.setNotes(notes);
            }
        });
    }
}