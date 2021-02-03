package com.library.bookforyou.models;

public enum Place {
    HOME("Home"), READING_ROOM("Reading Room");

    public final String place;

    private Place(String place) {
       this.place = place;
    }
}
