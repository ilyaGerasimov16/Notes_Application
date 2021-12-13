package com.example.notesapplication.data;

public class NoteData {
    private final String tittle;
    private final String description;
    private final int picture;
    private final boolean like;

    public NoteData(String tittle, String description, int picture, boolean like) {
        this.tittle = tittle;
        this.description = description;
        this.picture = picture;
        this.like = like;
    }

    public String getTittle() {
        return tittle;
    }

    public String getDescription() {
        return description;
    }

    public int getPicture() {
        return picture;
    }

    public boolean isLike() {
        return like;
    }
}
