package com.example.notesapplication.observe;

import com.example.notesapplication.data.NoteData;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private List<Observer> observers;

    public Publisher() {
        observers = new ArrayList<>();
    }

    public void subscribe(Observer obsrver){
        observers.add(obsrver);
    }

    public void unsubscribe(Observer obsrver){
        observers.remove(obsrver);
    }

    public void notifySingle(NoteData noteData){
        for(Observer observer: observers){
            observer.updateNoteData(noteData);
            unsubscribe(observer);
        }
    }
}