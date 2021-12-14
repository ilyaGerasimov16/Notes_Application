package com.example.notesapplication.data;

import android.content.res.Resources;
import android.content.res.TypedArray;

import com.example.notesapplication.R;

import java.util.ArrayList;
import java.util.List;

public class NotesDataImpl implements NotesSource{

    private List<NoteData> dataSource;
    private Resources resources;

    public NotesDataImpl(Resources resources) {
        dataSource = new ArrayList<>(7);
        this.resources = resources;
    }

    public NotesSource init() {
        String[] tittles = resources.getStringArray(R.array.titles);
        String[] descriptions = resources.getStringArray(R.array.descriptions);
        int[] pictures = getImageArray();
        int length = descriptions.length;
        for (int i = 0; i<length;i++) {
            dataSource.add(new NoteData(tittles[i],descriptions[i], pictures[i], false));
        }
        return this;
    }

    private int[] getImageArray() {
        TypedArray pictures = resources.obtainTypedArray(R.array.pictures);
        int length = pictures.length();
        int[] answer = new int[length];
        for(int i = 0; i<length; i++) {
            answer[i] = pictures.getResourceId(i,0);
        }
        pictures.recycle();
        return answer;
    }


    @Override
    public NoteData getNoteData(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public void deleteNoteData(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateNoteData(int position, NoteData noteData) {
        dataSource.set(position, noteData);
    }

    @Override
    public void addNoteData(NoteData noteData) {
        dataSource.add(noteData);
    }

    @Override
    public void clearNoteData() {
        dataSource.clear();
    }
}
