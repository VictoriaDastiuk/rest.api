package com.myNotes.rest.api.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.myNotes.rest.api.model.Note;
import com.myNotes.rest.api.model.Profile;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
@Getter
@Setter
public class NoteList {


    public List<Note> noteList = new ArrayList<Note>();

    public void addNote(Note note) {
        noteList.add(note);
    }

    @PostConstruct
    private void addToListNotesFromFile() throws IOException, JSONException {
        // Створити об'єкт File
        File file = new File("Notes.txt");

        // Створити об'єкт FileReader
        FileReader fileReader = new FileReader(file);

        // Створити об'єкт BufferedReader
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Читати JSON з BufferedReader
        String json = bufferedReader.readLine();

        // Створити список нотаток з JSON
        List<Note> notes = GetNotesFromJson(json);

        // Закрити файл
        fileReader.close();
        bufferedReader.close();

        // Закрити файл
        fileReader.close();
        bufferedReader.close();

        setNoteList(notes);
    }

        private List<Note> GetNotesFromJson(String json) throws JSONException {
            // Створити JSONArray з JSON
            JSONArray notesArray = new JSONArray(json);

            // Створити список нотаток
            List<Note> notes = new ArrayList<>();

            // Створити кожну нотатку з JSONArray
            for (int i = 0; i < notesArray.length(); i++) {
                JSONObject noteObject = notesArray.getJSONObject(i);

                // Створити Note з JSON
                Note note = new Note();
                note.setNameNote(noteObject.getString("nameName"));
                note.setStatusNote(noteObject.getString("statusNote"));
                note.setTextNote(noteObject.getString("TextNote"));
                note.setTitleNote(noteObject.getString("TitleNote"));
                note.setAddDate(noteObject.getString("AddDate"));
                note.setId(noteObject.getString("Id"));
                note.setModifyDate(noteObject.getString("ModifyDate"));
                note.setUserID(noteObject.getInt("UserID"));

                // Додати нотатку в список
                notes.add(note);
            }

            return notes;
        }}

//        String NameFile = "Notes.txt";
//        String[] listNotes = FilesNotes.FindAndReturnInfoFromFile(NameFile);
//        if (listNotes != null) {
//            // Створити список об'єктів
//            // Перетворити інформацію з файлу на об'єкти
//            for (String line : listNotes) {
//                // Розбити рядок на поля
//                String[] fields = line.split(" ", 11);
//
//                // Створити новий об'єкт
//                Note note = new Note();
//                note.setNameNote(fields[0]);
//                note.setTitleNote(fields[1]);
//                note.setTextNote(fields[2]);
//                note.setAddDate(fields[3] + fields[4]);
//                note.setModifyDate(fields[5] + fields[6]);
//                note.setStatusNote(fields[7]);
//                note.setId(fields[8]);
//                note.setUserID(Integer.parseInt(fields[9]));
//
//                // Додати новий об'єкт до списку
//                addNote(note);
//    }}}}
