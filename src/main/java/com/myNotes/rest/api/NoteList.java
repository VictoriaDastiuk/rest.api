package com.myNotes.rest.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class NoteList {


    public static List<Note> noteList = new ArrayList<Note>();


    public static List<Note> getNoteList() {
        return noteList;
    }

    public static void setNoteList(List<Note> noteList) {
        NoteList.noteList = noteList;
    }
}
