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
        List<Note> updatedNotes = NoteList.getNoteList();
        updatedNotes.add((Note) Note);
        NoteList.setNoteList(updatedNotes);
    }

    //    Знайти по заголовку нотатку
    public UUID findInNoteListbyTitle(String title) {
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
    public UUID findInNoteListbyName(String name) {
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
    public UUID howFindNote(String param, String valueParamFind)
    {
        UUID id = null;
        if (param.equals("заголовок")) {
            id = getInstance().findInNoteListbyTitle(valueParamFind);
        }
        if (param.equals("назва")){
            id = getInstance().findInNoteListbyName(valueParamFind);
        }
        return id;
    }

    //   Зміна нотатки
    public void changeNote (UUID ID, String name, String title, String text) {
        Note note;
        String formattedDate = getInstance().getDate();
        note = NotesController.findInNoteListbyID(ID);
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

    public void changeAllNote(UUID ID, String title, String name, String text){
        Note note;
        String formattedDate = getInstance().getDate();
        note = NotesController.findInNoteListbyID(ID);
        Objects.requireNonNull(note).setModifyDate(formattedDate);
        Objects.requireNonNull(note).setStatusNote("Modified");
        Objects.requireNonNull(note).setNameNote(name);
        Objects.requireNonNull(note).setTitleNote(title);
        Objects.requireNonNull(note).setTextNote(text);
    }

    public void delNote (UUID ID) {
        Note note;
        note = NotesController.findInNoteListbyID(ID);
        Objects.requireNonNull(note).setStatusNote("Deleted");

    }

    public String getDate (){
        //  визначення сьогоднішньої дати
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public UUID preFindNote (String howFind, String valueParamFind){
        //айдішнік нотатки виводить
        return getInstance().howFindNote(howFind, valueParamFind);
    }

    public void WantChangeNote(String name, String title, String text, String email, String howFind, String valueParamFind) {
//        питаєм по чому робим пошук нотатки? і виводить айді нотатки
        UUID idNote = getInstance().preFindNote(howFind,valueParamFind);

        if (idNote == null) {
            //якшо не нашли нотатку
            System.exit(400);
        } else {
            //resultOfFindNote - ID Note
            //param - нове значення параметру який міняєм
            //whatChange - що треба змінити в нотатці
            getInstance().changeNote(idNote,name,title,text);

        }
    }


    public void deleteNote(String howFind, String valueParamFind, String email) throws IOException, ClassNotFoundException {
        int userID = profilesContr.findInProfileList(email);
//        питаєм по чому робим пошук нотатки? і виводить айді нотатки
        UUID idNote = getInstance().preFindNote(howFind, valueParamFind);

        if (idNote == null) {
            //якшо не нашли нотатку
            System.exit(400);
        } else {
            getInstance().delNote(idNote);
            FilesNotes.AddNoteInFile(userID,idNote);
        }
    }

    public void makeNote(String name, String title, String text, String email) throws IOException, ClassNotFoundException {
        int userID = profilesContr.findInProfileList(email);
        //  визначення сьогоднішньої дати
        String formattedDate = getInstance().getDate();

        Note note = new Note(userID);
        UUID ID = note.getId();
        getInstance().changeAllNote(ID,title,name,text);

        // додавання в файл нотатки
        FilesNotes.AddNoteInFile(userID, ID);
    }

    public void ShowNoteList(String email){
        int userID = profilesContr.findInProfileList(email);

        for (Note note : NoteList.getNoteList()) {
            if ((note.getStatusNote().equals("Created") || note.getStatusNote().equals("Modified")) && (userID == note.getUserID())){
                System.out.println(note.getNameNote() + ": " + note.getTitleNote() + " Текст нотатки:" + note.getTextNote());
            }
        }
    }
    public void WantShowNote (String email, String howFind, String valueParamFind){
        int userID = profilesContr.findInProfileList(email);

        // питаєм по чому робим пошук нотатки? і виводить айді нотатки
        UUID idNote = getInstance().preFindNote(howFind,valueParamFind);

        if (idNote == null) {
            //якшо не нашли нотатку
            System.exit(400);
        } else {
            getInstance().showNote(idNote);
        }
    }

    public String showNote(UUID resultOfFindNote) {
        Note note  = NotesController.findInNoteListbyID(resultOfFindNote);
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
