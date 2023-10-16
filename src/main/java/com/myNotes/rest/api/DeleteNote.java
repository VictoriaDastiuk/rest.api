package com.myNotes.rest.api;

import java.util.UUID;

import static com.myNotes.rest.api.Auth.scanner;

public class DeleteNote {

    public static void deleteNote(int userID){
//        питаєм по чому робим пошук нотатки? і виводить айді нотатки
        UUID resultOfFindNote = NotesController.preFindNote();

        if (resultOfFindNote == null) {
            //якшо не нашли нотатку
            WrittingForClient.dontFind();
        } else {
            NotesController.delNote(resultOfFindNote);
        }
    }
}
