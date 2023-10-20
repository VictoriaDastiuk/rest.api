package com.myNotes.rest.api;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class NotesController {

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

    public void createNote(int userID){
        Note note = new Note(userID);
        getInstance().addNote(note);
    }

    public void addNote(Object Note) {
        List<Note> updatedNotes = NoteListInst.getNoteList();
        updatedNotes.add((Note) Note);
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
    public Note findInNoteListbyID(UUID ID, int userID) {
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
            id = getInstance().findInNoteListbyTitle(valueParamFind,userID);
        }
        if (param.equals("назва")){
            id = getInstance().findInNoteListbyName(valueParamFind, userID);
        }
        return id;
    }

    //   Зміна нотатки
    public void changeNote (UUID ID, String name, String title, String text, int userID) {
        Note note;
        String formattedDate = getInstance().getDate();
        note = getInstance().findInNoteListbyID(ID, userID);
        Objects.requireNonNull(note).setModifyDate(formattedDate);
        Objects.requireNonNull(note).setStatusNote("Modified");

        if (Objects.nonNull(title)) {
            Objects.requireNonNull(note).setTitleNote(title);
        }
        if (Objects.nonNull(name)){
            Objects.requireNonNull(note).setNameNote(name);
        }
        if (Objects.nonNull(text)) {
            Objects.requireNonNull(note).setTextNote(text);
        }
    }

    public void changeAllNote(UUID ID, String title, String name, String text, int userID){
        Note note;
        String formattedDate = getInstance().getDate();
        note = getInstance().findInNoteListbyID(ID, userID);
        note.setModifyDate(formattedDate);
        note.setStatusNote("Modified");
        note.setNameNote(name);
        note.setTitleNote(title);
        note.setTextNote(text);
    }

    public void delNote (UUID ID, int userID) {
        Note note;
        note = getInstance().findInNoteListbyID(ID,userID);
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
        return getInstance().howFindNote(howFind, valueParamFind, userID);
    }

    public void WantChangeNote(String email, String name, String title, String text, String howFind, String valueParamFind) {
        int userID = profilesContr.findInProfileList(email);
//        питаєм по чому робим пошук нотатки? і виводить айді нотатки
        UUID idNote = getInstance().howFindNote(howFind,valueParamFind,userID);

        if (idNote == null) {
            //якшо не нашли нотатку
            System.exit(400);
        } else {
            //resultOfFindNote - ID Note
            //param - нове значення параметру який міняєм
            //whatChange - що треба змінити в нотатці
            getInstance().changeNote(idNote,name,title,text, userID);

        }
    }


    public void deleteNote(String howFind, String valueParamFind, String email) throws IOException, ClassNotFoundException {
        int userID = profilesContr.findInProfileList(email);
//        питаєм по чому робим пошук нотатки? і виводить айді нотатки
        UUID idNote = getInstance().howFindNote(howFind, valueParamFind, userID);

        if (idNote == null) {
            //якшо не нашли нотатку
            System.exit(400);
        } else {
            getInstance().delNote(idNote, userID);
            FilesNotes file = new FilesNotes();
            file.AddNoteInFile(userID,idNote);
        }
    }

    public void makeNote(String name, String title, String text, String email) throws IOException, ClassNotFoundException {
        int userID = profilesContr.findInProfileList(email);
        //  визначення сьогоднішньої дати
        String formattedDate = getInstance().getDate();

        Note note = new Note(userID);
        UUID ID = note.getId();
        getInstance().createNote(userID);
        getInstance().changeAllNote(ID,title,name,text, userID);

        // додавання в файл нотатки
        FilesNotes file = new FilesNotes();
        file.AddNoteInFile(userID, ID);
    }

    public void ShowNoteList(String email){
        int userID = profilesContr.findInProfileList(email);

        for (Note note : NoteListInst.getNoteList()) {
            if ((note.getStatusNote().equals("Created") || note.getStatusNote().equals("Modified")) && (userID == note.getUserID())){
                System.out.println(note.getNameNote() + ": " + note.getTitleNote() + " Текст нотатки:" + note.getTextNote());
            }
        }
    }
    public void WantShowNote (String email, String howFind, String valueParamFind){
        int userID = profilesContr.findInProfileList(email);

        // питаєм по чому робим пошук нотатки? і виводить айді нотатки
        UUID idNote = getInstance().howFindNote(howFind,valueParamFind, userID);
        if (idNote == null) {
            //якшо не нашли нотатку
            System.exit(400);
        } else {
            getInstance().showNote(idNote, userID);
        }
    }

    public String showNote(UUID resultOfFindNote, int userID) {
        Note note  = getInstance().findInNoteListbyID(resultOfFindNote, userID);
        String ans;
        //показуємо текст нотатки
        assert note != null;
        if (note.getStatusNote().equals("Created") || note.getStatusNote().equals("Modified"))
        {
            ans = note.getNameNote() + ": " + note.getTitleNote() + " Текст нотатки:" + note.getTextNote();
        }
        else {
            ans = "У вас немає такої нотатки або ж вона вже видалена";
        }
        return ans;
    }
}
