package com.myNotes.rest.api.services;

import com.myNotes.rest.api.model.Note;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
@Getter
@Setter
public class NoteList {


    public List<Note> noteList = new ArrayList<Note>();

//    private static NoteList instance;
//    NoteList() {
//    }
//    public static NoteList getInstance() {
//        if (instance == null) {
//            instance = new NoteList();
//        }
//        return instance;
//    }
}