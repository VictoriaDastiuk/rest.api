package com.myNotes.rest.api.services;

import ch.qos.logback.core.net.AutoFlushingObjectWriter;
import com.myNotes.rest.api.model.Note;
import com.myNotes.rest.api.model.Profile;
import jakarta.annotation.PreDestroy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.usermodel.XSSFWorkbook;
@Service
public class FilesNotes {
    File folder = new File("./Notes");
    @Autowired
    NotesController NotesContr;

    public static String[] FindAndReturnInfoFromFile(String nameFile) throws IOException {
        try {
            // Отримати поточку до папки проекту
            File projectDir = new File(".").getAbsoluteFile();
            // масив файлів і папок
            File[] files = projectDir.listFiles();

            for (File file : files) {
                if (file.getName().equals(nameFile)) {
                    if (file.exists()) {

                        String[] lines = Files.readAllLines(file.toPath()).toArray(new String[0]);
                        return lines;
                    } else {
                        return null;
                    }
                }
            }

        } catch (IOException e) {
            return null;
        }
        return null;
    }

    public static void AddProfileInFile(List<Profile> ListProfile) throws IOException, ClassNotFoundException {
        File file = new File("Profiles.txt");
        FileWriter writer = new FileWriter(file, Charset.forName("UTF-8"));

        // Записати профілі у файл, розділені комою
        for (Profile profile : ListProfile) {
            writer.write(profile.getName() + " " + profile.getEmail() + " " + profile.getUserID() + "\n");
        }
        writer.close();
    }
        public void AddNoteInFile(List<Note> notes) throws IOException, ClassNotFoundException, JSONException {
            JSONArray arr = new JSONArray();
            for (Note note : notes) {
                JSONObject noteObject = new JSONObject();
                noteObject.put("nameName", note.getNameNote());
                noteObject.put("statusNote", note.getStatusNote());
                noteObject.put("TextNote", note.getTextNote());
                noteObject.put("TitleNote", note.getTitleNote());
                noteObject.put("AddDate", note.getAddDate());
                noteObject.put("Id", note.getId());
                noteObject.put("ModifyDate", note.getModifyDate());
                noteObject.put("UserID", note.getUserID());
                arr.put(noteObject);
            }
            File file = new File("Notes.txt");
            FileWriter writer = new FileWriter(file, false);
            writer.write(arr.toString());
            writer.close();
        }}
//public void AddNoteInFile(List<Note> notes) throws IOException, ClassNotFoundException, JSONException {
//    JSONObject jsonObject = new JSONObject();
//    File file = new File("Notes.txt");
//    FileWriter writer = new FileWriter(file, false);
//    JSONArray arr = new JSONArray(notes);
//    System.out.println(arr.toString());
//    writer.write(arr.toString());
//    writer.close();
//}}
//public void AddNoteInFile(List<Note> notes) throws IOException, ClassNotFoundException, JSONException {
//    JSONObject jsonObject = new JSONObject();
//    File file = new File("Notes.txt");
//    FileWriter writer = new FileWriter(file, false);
//    JSONArray arr = new JSONArray();
//    for(Note note: notes){
//        JSONObject jo = new JSONObject(note);
//        arr.put(jo);
//    }
//    writer.write(arr.toString());
//    writer.close();
//}}

// Записати JSONArray у файл

//
//    public void AddNoteInFile(List<Note> notes) throws IOException, ClassNotFoundException, JSONException {
//        JSONObject jsonObject = new JSONObject(String.valueOf(notes));
//        File file = new File("Notes.txt");
//        FileWriter writer = new FileWriter(file, false);
//        for(Note note: notes){
//        jsonObject.put("nameName", note.getNameNote());
//        jsonObject.put("statusNote", note.getStatusNote());
//        jsonObject.put("TextNote", note.getTextNote());
//        jsonObject.put("TitleNote", note.getTitleNote());
//        jsonObject.put("AddDate", note.getAddDate());
//        jsonObject.put("Id", note.getId());
//        jsonObject.put("ModifyDate", note.getModifyDate());
//        jsonObject.put("UserID", note.getUserID());
//        writer.write(jsonObject.toString());
//        writer.close();
//        }
//    }

    // Записати профілі у файл, розділені комою Charset.forName("UTF-8")
//        for (Note note : notes) {
//            writer.write(note.getNameNote() + " " + note.getTitleNote() + " " + note.getTextNote() + " " + note.getAddDate() + " " + note.getModifyDate() + " " + note.getStatusNote() + " " + note.getId() + " " + note.getUserID() + "\n");
//        }
