package com.example.notesapplication.data;

public interface NotesSource {
    NoteData getNoteData(int position);
    int size();
}
