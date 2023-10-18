package com.myNotes.rest.api;

import java.io.IOException;
import java.util.UUID;

import static com.myNotes.rest.api.Auth.scanner;

public class DeleteNote {

    public static void deleteNote(int userID) throws IOException, ClassNotFoundException {
//        питаєм по чому робим пошук нотатки? і виводить айді нотатки
        UUID resultOfFindNote = NotesController.preFindNote();

        NotesController.delNote(resultOfFindNote);
        FilesNotes.AddNoteInFile(userID,resultOfFindNote);
        if (resultOfFindNote == null) {
            //якшо не нашли нотатку
            WrittingForClient.dontFind();
        } else {
            NotesController.delNote(resultOfFindNote);
        }
    }
}
