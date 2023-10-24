package com.myNotes.rest.api;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
public class NotesController {
    String result;

    private static NotesController instance;
    ProfilesController profilesContr = ProfilesController.getInstance();
    NoteList NoteListInst = NoteList.getInstance();


    private NotesController() {
    }
    public static NotesController getInstance() {
        if (instance == null) {
            instance = new NotesController();
        }
        return instance;
    }

    public Note createNote(int userID){
        Note note = new Note(userID);
        note.setAddDate(NotesController.getInstance().getDate());
        return note;
    }

    public void addNote(Note note) {
        List<Note> updatedNotes = NoteListInst.getNoteList();
        updatedNotes.add(note);
        NoteListInst.setNoteList(updatedNotes);
    }

    //    Знайти по заголовку нотатку
    public UUID findInNoteListbyTitle(String title, int userID) {
        for (Note note : NoteListInst.getNoteList())
        {
            if (note.getTitleNote().equals(title) && note.getUserID()== userID)
            {
                return note.getId();
            }
        }
        return null;
    }
    //    знайти по айді
    public Note findInNoteListbyID(UUID ID, int userID){
        for (Note note : NoteListInst.getNoteList())
        {
            if (note.getId().equals(ID) && note.getUserID()== userID)
            {
                return note;
            }
        }
        return null;
    }

    //    Знайти по назві нотатку
    public UUID findInNoteListbyName(String name, int userID) {
        for (Note note : NoteListInst.getNoteList())
        {
            if (note.getTitleNote().equals(name) && note.getUserID()== userID)
            {
                return note.getId();
            }
        }
        return null;
    }

    //    Розмір списку нотаток
//    public int getSizeList(){
//        return NoteListInst.noteList.size();
//    }

    //    МЕТОД ПОШУКУ НОТАТКИ
    public UUID howFindNote(String param, String valueParamFind,int userID)
    {
        UUID id = null;
        if (param.equals("заголовок")) {
            id = findInNoteListbyTitle(valueParamFind,userID);
        }
        if (param.equals("назва")){
            id = findInNoteListbyName(valueParamFind, userID);
        }
        return id;
    }

    //   Зміна нотатки
    public Note changeNote (UUID ID, String name, String title, String text, int userID) {
        Note note;
        String formattedDate = getDate();
        note = findInNoteListbyID(ID, userID);
        note.setModifyDate(formattedDate);
        note.setStatusNote("Modified");

        if (title!=null) {
            note.setTitleNote(title);
        }
        if (name!=null){
            note.setNameNote(name);
        }
        if (text!=null) {
            note.setTextNote(text);
        }
        return note;
    }

    public void changeAllNote(UUID ID, String title, String name, String text, int userID){
        Note note;
        String formattedDate = getDate();
        note = findInNoteListbyID(ID, userID);
        note.setModifyDate(formattedDate);
        note.setStatusNote("Modified");
        note.setNameNote(name);
        note.setTitleNote(title);
        note.setTextNote(text);
    }

    public void delNote (UUID ID, int userID) {
        Note note;
        note = findInNoteListbyID(ID,userID);
        note.setStatusNote("Deleted");

    }

    public String getDate (){
        //  визначення сьогоднішньої дати
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public UUID preFindNote (String howFind, String valueParamFind, int userID){
        //айдішнік нотатки виводить
        return howFindNote(howFind, valueParamFind, userID);
    }

    public String WantChangeNote(String email, String name, String title, String text, String howFind, String valueParamFind) throws IOException, ClassNotFoundException {
       try {
           int userID = profilesContr.getUserIDFromList(email);
           List<Note> infoFromFile = FilesNotes.returnNotesFromFile(userID);
           if (infoFromFile!= null) {
               NoteListInst.setNoteList(infoFromFile);
           }

//        питаєм по чому робим пошук нотатки? і виводить айді нотатки
           UUID idNote = howFindNote(howFind,valueParamFind,userID);

           if (idNote == null) {
               //якшо не нашли нотатку
                result = "не знайли нотатку";
           } else {
               //resultOfFindNote - ID Note
               //param - нове значення параметру який міняєм
               //whatChange - що треба змінити в нотатці
               Note note = changeNote(idNote,name,title,text, userID);
               // додавання в файл нотатки
               FilesNotes file = new FilesNotes();
               file.AddNoteInFile(userID, note.getId(),NoteListInst.getNoteList());
               result = "ok";

           }
       }catch (IOException e){
           result = "error";
       }
       return result;
    }


    public String deleteNote(String howFind, String valueParamFind, String email) throws IOException, ClassNotFoundException {
        int userID = profilesContr.getUserIDFromList(email);
        try {
            List<Note> infoFromFile = FilesNotes.returnNotesFromFile(userID);

            if (infoFromFile!= null) {
                //        питаєм по чому робим пошук нотатки? і виводить айді нотатки
                UUID idNote = howFindNote(howFind, valueParamFind, userID);

                if (idNote == null) {
                    //якшо не нашли нотатку
                    return "ERROR";
                } else {
                    delNote(idNote, userID);
                    FilesNotes file = new FilesNotes();
                    file.AddNoteInFile(userID,idNote,NoteListInst.getNoteList());
                    return "ok";
                }
            }
        }
        catch (IOException e) {
            return "ERROR";
        }
        return "ERROR";
    }

    public String makeNote(String name, String title, String text, String email) throws IOException, ClassNotFoundException {
        int userID = profilesContr.getUserIDFromList(email);
        try {
            //  визначення сьогоднішньої дати
            String formattedDate = getDate();

            Note note = createNote(userID);
            addNote(note);
            changeAllNote(note.getId(),title,name,text, userID);

            // додавання в файл нотатки
            FilesNotes file = new FilesNotes();
            file.AddNoteInFile(userID, note.getId(),NoteListInst.getNoteList());
            return "ok";
        }
        catch (IOException e)
        {
            return "error";
        }

    }

    public List<Note> ShowNoteList(String email) throws IOException, ClassNotFoundException {
        try {
        int userID = profilesContr.getUserIDFromList(email);
        List<Note> infoFromFile = FilesNotes.returnNotesFromFile(userID);
        if (infoFromFile!= null) {
            return infoFromFile;
        }
        }
        catch (IOException e) {
            return null;
        }
//        for (Note note : NoteListInst.getNoteList()) {
//            if ((note.getStatusNote().equals("Created") || note.getStatusNote().equals("Modified")) && (userID == note.getUserID())){
//                System.out.println(note.getNameNote() + ": " + note.getTitleNote() + " Текст нотатки:" + note.getTextNote());
//            }
//        }
        return null;
    }
    public Note WantShowNote (String email, String howFind, String valueParamFind) {
        int userID = profilesContr.getUserIDFromList(email);

        // питаєм по чому робим пошук нотатки? і виводить айді нотатки
        UUID idNote = howFindNote(howFind, valueParamFind, userID);
        if (idNote == null) {
            //якшо не нашли нотатку
            return null;
        } else {
            return findInNoteListbyID(idNote, userID);
        }
    }
}
//
//    public String showNote(UUID resultOfFindNote, int userID) {
//        String ans;
//        //показуємо текст нотатки
//        assert note != null;
//        if (note.getStatusNote().equals("Created") || note.getStatusNote().equals("Modified"))
//        {
//            NoteListInst.getNoteList()
////            ans = note.getNameNote() + ": " + note.getTitleNote() + " Текст нотатки:" + note.getTextNote();
//        }
//        else {
//            ans = "У вас немає такої нотатки або ж вона вже видалена";
//        }
//        return ans;
//    }
//}
