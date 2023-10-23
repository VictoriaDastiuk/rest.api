package com.myNotes.rest.api;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
    //import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.usermodel.XSSFWorkbook;
    public class FilesNotes {
        File folder = new File("./Notes");
        NotesController NotesContr = NotesController.getInstance();
        NoteList NoteListContr = NoteList.getInstance();

        public static void NewNotesFile(int userID) throws IOException {
            FileOutputStream newFile = new FileOutputStream("notes_" + userID + ".txt");
        }

        public static File FindFile(int userID) throws IOException {
            File directory = new File("/home/DN180996DVA/IdeaProjects/rest.api");

            File file = new File(directory, "notes_" + userID + ".txt");

            if (file.exists()) {
                return file;
            } else {
                return null;
            }
        }

        public static List<Note> getInfoFromFile(int userID, File file) throws IOException, ClassNotFoundException {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                List<Note> notes = (List<Note>) objectInputStream.readObject();
                fileInputStream.close();
                return notes;
            } catch (IOException e){
                return null;
            }
        }

        public static List<Note> returnNotesFromFile(int userID) throws IOException, ClassNotFoundException {
            File file = FilesNotes.FindFile(userID);

            if (file==null){
                FilesNotes.NewNotesFile(userID);
                file = FilesNotes.FindFile(userID);
                return null;
            }
            return FilesNotes.getInfoFromFile(userID,file);
        }
        public void AddNoteInFile(int userID, UUID id, List<Note> infoFromFile) throws IOException, ClassNotFoundException {
//            File file = FilesNotes.FindFile(userID);
//            if (file==null){
//                FilesNotes.NewNotesFile(userID);
//                file = FilesNotes.FindFile(userID);
//            }
//
//                ArrayList<Note> infoFromFile = FilesNotes.getInfoFromFile(userID,file);
                Note note = NotesContr.findInNoteListbyID(id,userID);
                if (infoFromFile!= null){
                infoFromFile.add(note);
                }
                else {
                    infoFromFile = (List<Note>) NoteListContr.getNoteList();
                }

            // Створити об'єкт ObjectOutputStream
            FileOutputStream fileOutputStream = new FileOutputStream("notes_" + userID + ".txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(infoFromFile);

            // Закрити об'єкти
            objectOutputStream.close();
            fileOutputStream.close();

        }
    }

