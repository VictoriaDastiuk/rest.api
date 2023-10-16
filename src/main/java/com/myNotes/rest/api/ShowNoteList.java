package com.myNotes.rest.api;

public class ShowNoteList {
    public static void ShowNoteList(int userID){

        for (Note note : NoteList.getNoteList()) {
            if ((note.getStatusNote().equals("Created") || note.getStatusNote().equals("Modified")) && (userID == note.getUserID())){
                System.out.println(note.getNameNote() + ": " + note.getTitleNote() + " Текст нотатки:" + note.getTextNote());
            }
        }
    }
}
