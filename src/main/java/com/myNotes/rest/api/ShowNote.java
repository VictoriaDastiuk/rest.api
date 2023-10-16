package com.myNotes.rest.api;

import java.util.UUID;

import static com.myNotes.rest.api.WrittingForClient.scanner;

public class ShowNote {
    public static void WantShowNote (int userID){
        // питаєм по чому робим пошук нотатки? і виводить айді нотатки
        UUID resultOfFindNote = NotesController.preFindNote();

        if (resultOfFindNote == null) {
            //якшо не нашли нотатку
            WrittingForClient.dontFind();
        } else {
            WrittingForClient.showNote(resultOfFindNote);
        }
    }
}
