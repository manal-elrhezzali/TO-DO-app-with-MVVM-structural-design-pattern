package me.elrhezzalimanal.simpletodoappwithmvvm;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Room annotation at compile time it will create all the necessary code to create a SQLite table for this object
@Entity(tableName = "note_table")
public class Note {
    //Room will automatically generate columns for these fields
    @PrimaryKey(autoGenerate = true) //with each new row we add to this note table SQLite will automatically increment the id and add it in the id column
    private int id;

    private String title;

    private String description;

    private int priority;

    //we need a constructor so that Room can recreate Note objects from db

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    //Room will use this setter to set an id for the Note objects that we create without passing id
    public void setId(int id) {
        this.id = id;
    }

    //inorder for Room to persist these fields in the db we need to add their getters


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

}
