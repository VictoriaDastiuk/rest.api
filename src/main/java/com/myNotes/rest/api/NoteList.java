package com.myNotes.rest.api;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class NoteList {

@Getter
@Setter
    public List<Note> noteList = new ArrayList<Note>();

    private static NoteList instance;
    NoteList() {
    }
    public static NoteList getInstance() {
        if (instance == null) {
            instance = new NoteList();
        }
        return instance;
    }
}
