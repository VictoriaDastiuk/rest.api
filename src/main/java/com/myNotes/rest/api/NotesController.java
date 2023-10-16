package com.myNotes.rest.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.myNotes.rest.api.ModelOfProject.scanner;

public class NotesController {
    public void createNote(int userID){
        Note note = new Note(userID);
        NotesController.addNote(note);
    }

    public static void addNote(Object Note) {
        List<Note> updatedNotes = NoteList.getNoteList();
        updatedNotes.add((Note) Note);
        NoteList.setNoteList(updatedNotes);
    }

    //    Знайти по заголовку нотатку
    public static UUID findInNoteListbyTitle(String title) {
        for (Note note : NoteList.getNoteList())
        {
            if (note.getTitleNote().equals(title))
            {
                return note.getId();
            }
        }
        return null;
    }
    //    знайти по айді
    public static Note findInNoteListbyID(UUID ID) {
        for (Note note : NoteList.getNoteList())
        {
            if (note.getId().equals(ID))
            {
                return note;
            }
        }
        return null;
    }

    //    Знайти по назві нотатку
    public static UUID findInNoteListbyName(String name) {
        for (Note note : NoteList.getNoteList())
        {
            if (note.getTitleNote().equals(name))
            {
                return note.getId();
            }
        }
        return null;
    }

    //    Розмір списку нотаток
    public int getSizeList(){
        return NoteList.noteList.size();
    }

    //    МЕТОД ПОШУКУ НОТАТКИ
    public static UUID howFindNote(String param, String how)
    {
        UUID id = null;
        if (how.equals("заголовок")) {
            id = NotesController.findInNoteListbyTitle(param);
        }
        if (how.equals("назва")){
            id = NotesController.findInNoteListbyName(param);
        }
        return id;
    }

    //   Зміна нотатки
    public static void changeNote (UUID ID, String param, String whatChange) {
        Note note;
        String formattedDate = NotesController.getDate();
        note = NotesController.findInNoteListbyID(ID);
        Objects.requireNonNull(note).setModifyDate(formattedDate);
        Objects.requireNonNull(note).setStatusNote("Modified");

        if (whatChange.equals("заголовок")) {
            Objects.requireNonNull(note).setTitleNote(param);
        }
        if (whatChange.equals("назву")){
            Objects.requireNonNull(note).setTitleNote(param);
        }
        if (whatChange.equals("текст")) {
            Objects.requireNonNull(note).setTextNote(param);
        }
    }

    public static void changeAllNote(UUID ID, String title, String name, String text){
        Note note;
        String formattedDate = NotesController.getDate();
        note = NotesController.findInNoteListbyID(ID);
        Objects.requireNonNull(note).setModifyDate(formattedDate);
        Objects.requireNonNull(note).setStatusNote("Modified");
        Objects.requireNonNull(note).setNameNote(name);
        Objects.requireNonNull(note).setTitleNote(title);
        Objects.requireNonNull(note).setTextNote(text);
    }

    public static void delNote (UUID ID) {
        Note note;
        note = NotesController.findInNoteListbyID(ID);
        Objects.requireNonNull(note).setStatusNote("Deleted");

    }

    public static String getDate (){
        //  визначення сьогоднішньої дати
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static UUID preFindNote (){
        // по чому робим пошук нотатки?
        WrittingForClient.howFind();
        String howFind = scanner.nextLine();

        WrittingForClient.writeParam();
        String paramToFind = scanner.nextLine();

        //айдішнік нотатки виводить
        return NotesController.howFindNote(howFind, paramToFind);
    }



}
