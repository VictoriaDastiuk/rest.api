package com.myNotes.rest.api.services;

import com.myNotes.rest.api.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NotesController {

    private static NotesController instance;
    @Autowired
    ProfilesController profilesContr;
    @Autowired
    NoteList NoteListInst;


    public Note createNote() {
        Note note = new Note();
        note.setAddDate(getDate());
        return note;
    }


    //    Знайти по заголовку нотатку
    public String findInNoteListbyTitle(String title, int userID) {
        for (Note note : NoteListInst.getNoteList()) {
            if (note.getTitleNote().equals(title) && note.getUserID() == userID) {
                return note.getId();
            }
        }
        return null;
    }

    //    знайти по айді
    public Note findInNoteListbyID(String ID, int userID) {
        for (Note note : NoteListInst.getNoteList()) {
            if (note.getId().equals(ID) && note.getUserID() == userID) {
                return note;
            }
        }
        return null;
    }

    //    Знайти по назві нотатку
    public String findInNoteListbyName(String name, int userID) {
        for (Note note : NoteListInst.getNoteList()) {
            if (note.getTitleNote().equals(name) && note.getUserID() == userID) {
                return note.getId();
            }
        }
        return null;
    }


    //    МЕТОД ПОШУКУ НОТАТКИ
    public String howFindNote(String param, String valueParamFind, int userID) {
        String id = null;
        if (param.equals("заголовок")) {
            id = findInNoteListbyTitle(valueParamFind, userID);
        }
        if (param.equals("назва")) {
            id = findInNoteListbyName(valueParamFind, userID);
        }
        return id;
    }

    //   Зміна нотатки
    public Note changeNote(String ID, String name, String title, String text, int userID) {
        Note note;
        String formattedDate = getDate();
        note = findInNoteListbyID(ID, userID);
        note.setModifyDate(formattedDate);
        note.setStatusNote("Modified");

        if (title != null) {
            note.setTitleNote(title);
        }
        if (name != null) {
            note.setNameNote(name);
        }
        if (text != null) {
            note.setTextNote(text);
        }
        return note;
    }

    public void changeAllNote(String ID, String title, String name, String text, int userID) {
        String formattedDate = getDate();
        List<Note> newlist = new ArrayList<>();
        if (NoteListInst.getNoteList().isEmpty()){
            Note notenew = new Note();
            notenew.setModifyDate(formattedDate);
            notenew.setStatusNote("Modified");
            notenew.setNameNote(name);
            notenew.setTitleNote(title);
            notenew.setTextNote(text);
            newlist.add(notenew);
            NoteListInst.setNoteList(newlist);
        }
        else{
        for (Note note : NoteListInst.getNoteList()) {
            if (note.getId().equals(ID) && note.getUserID() == userID) {
                note.setModifyDate(formattedDate);
                note.setStatusNote("Modified");
                note.setNameNote(name);
                note.setTitleNote(title);
                note.setTextNote(text);
            }
            newlist.add(note);
        }
        NoteListInst.setNoteList(newlist);
    }}

    public void delNote(String ID, int userID) {
        Note note;
        note = findInNoteListbyID(ID, userID);
        note.setStatusNote("Deleted");

    }

    public String getDate() {
        //  визначення сьогоднішньої дати
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public String WantChangeNote(String email, String name, String title, String text, String howFind, String valueParamFind) throws IOException, ClassNotFoundException {
        String result = profilesContr.checkEmailInProfilesList(email);
        if (result.equals("ok")) {
            int userID = Integer.parseInt(profilesContr.getUserIDFromList(email));
//            try {
//                List<Note> infoFromFile = NoteListInst.getNoteList();
//                if (infoFromFile != null) {
//                    NoteListInst.setNoteList(infoFromFile);
//                }

//        питаєм по чому робим пошук нотатки? і виводить айді нотатки
            String idNote = howFindNote(howFind, valueParamFind, userID);

            if (idNote == null) {
                //якшо не нашли нотатку
                result = "не знайли нотатку";
            } else {
                //resultOfFindNote - ID Note
                //param - нове значення параметру який міняєм
                //whatChange - що треба змінити в нотатці
                Note note = changeNote(idNote, name, title, text, userID);
                // додавання в файл нотатки
                FilesNotes file = new FilesNotes();
                file.AddNoteInFile(NoteListInst.getNoteList());
                result = "ok";

            }
        } else {
            result = "You haven`t profile";
        }
        return result;
    }

    public String deleteNote(String howFind, String valueParamFind, String email) throws IOException, ClassNotFoundException {
        String result = profilesContr.checkEmailInProfilesList(email);
        if (result.equals("ok")) {
            int userID = Integer.parseInt(profilesContr.getUserIDFromList(email));
            //        питаєм по чому робим пошук нотатки? і виводить айді нотатки
            String idNote = howFindNote(howFind, valueParamFind, userID);

            if (idNote == null) {
                //якшо не нашли нотатку
                return "ERROR";
            } else {
                delNote(idNote, userID);
                FilesNotes file = new FilesNotes();
                file.AddNoteInFile(NoteListInst.getNoteList());
                return "ok";
            }
        } else {
            return "You haven`t profile";
        }
    }

    public String makeNote(String name, String title, String text, String email) throws IOException, ClassNotFoundException {
        String result = profilesContr.checkEmailInProfilesList(email);
        if (result.equals("ok")) {
            int userID = Integer.parseInt(profilesContr.getUserIDFromList(email));
            try {
                //  визначення сьогоднішньої дати
                String formattedDate = getDate();

                Note note = createNote();
                note.setStatusNote("Created");
                note.setId(name + "_" + userID);
                note.setUserID(userID);

                changeAllNote(note.getId(), title, name, text, userID);
//                NoteListInst.addNote(note);

                // додавання в файл нотатки
                FilesNotes file = new FilesNotes();
                file.AddNoteInFile(NoteListInst.getNoteList());
                return "ok";
            } catch (IOException e) {
                return "error";
            }
        } else {
            return "You haven`t profile";
        }
    }

    public List<Note> ShowNoteList(String email) throws IOException, ClassNotFoundException {
        String result = profilesContr.checkEmailInProfilesList(email);
        if (result.equals("ok")) {
            int userID = Integer.parseInt(profilesContr.getUserIDFromList(email));
            List<Note> userNote = new ArrayList<Note>();
            for (Note note : NoteListInst.getNoteList()) {
                if (note.getUserID() == userID) {
                    userNote.add(note);
                }
            }
        }
        return null;
    }

    public Note WantShowNote(String email, String howFind, String valueParamFind) throws IOException, ClassNotFoundException {
        String result = profilesContr.checkEmailInProfilesList(email);
        if (result.equals("ok")) {
            int userID = Integer.parseInt(profilesContr.getUserIDFromList(email));
            // питаєм по чому робим пошук нотатки? і виводить айді нотатки
            String idNote = howFindNote(howFind, valueParamFind, userID);
            if (idNote == null) {
                //якшо не нашли нотатку
                return null;
            } else {
                return findInNoteListbyID(idNote, userID);
            }
        }
        return null;
    }
}
