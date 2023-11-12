package com.myNotes.rest.api.services;

import com.myNotes.rest.api.model.Note;
import com.myNotes.rest.api.model.Profile;
import jakarta.annotation.PostConstruct;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilesNotes {
    @Autowired
    NoteList noteListInst;
    @Autowired
    ProfileList profileListInst;

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

        noteListInst.setNoteList(notes);
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
    }

    @PostConstruct
    private void addToListProfilesFromFile() throws IOException, JSONException {
        // Створити об'єкт File
        File file = new File("Profiles.txt");

        // Створити об'єкт FileReader
        FileReader fileReader = new FileReader(file);

        // Створити об'єкт BufferedReader
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Читати JSON з BufferedReader
        String json = bufferedReader.readLine();

        // Створити список нотаток з JSON
        List<Profile> profiles = GetProfilesFromJson(json);

        // Закрити файл
        fileReader.close();
        bufferedReader.close();

        // Закрити файл
        fileReader.close();
        bufferedReader.close();

        profileListInst.setProfileList(profiles);
    }

    private List<Profile> GetProfilesFromJson(String json) throws JSONException {
        // Створити JSONArray з JSON
        JSONArray profileArray = new JSONArray(json);

        // Створити список нотаток
        List<Profile> profiles = new ArrayList<>();

        // Створити кожну нотатку з JSONArray
        for (int i = 0; i < profileArray.length(); i++) {
            JSONObject profileObject = profileArray.getJSONObject(i);

            // Створити Note з JSON
            Profile profile = new Profile();
            profile.setName(profileObject.getString("Name"));
            profile.setEmail(profileObject.getString("email"));
            profile.setUserID(profileObject.getInt("userID"));

            // Додати нотатку в список
            profiles.add(profile);
        }

        return profiles;
    }

    @Scheduled(fixedDelay = 60)
    public void AddNoteInFile(List<Note> notes) throws IOException {
        File file = new File("Notes.txt");
        FileWriter writer = new FileWriter(file, false);
        JSONArray arr = new JSONArray(notes);
        System.out.println(arr.toString());
        writer.write(arr.toString());
        writer.close();
    }

    public void AddProfileInFile(List<Profile> profiles) throws IOException {
        File file = new File("Profiles.txt");
        FileWriter writer = new FileWriter(file, false);
        JSONArray arr = new JSONArray(profiles);
        System.out.println(arr.toString());
        writer.write(arr.toString());
        writer.close();
    }
}