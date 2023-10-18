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
import java.util.UUID;
import java.util.stream.IntStream;

    //import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.usermodel.XSSFWorkbook;
    public class FilesNotes {
        File folder = new File("./Notes");

        public static void NewNotesFile(int userID) throws IOException {
            FileOutputStream newFile = new FileOutputStream("notes_" + userID + ".txt");
        }

        public static Path FindFile(int userID) throws IOException, ClassNotFoundException {
            Path directory = Paths.get("/home/DN180996DVA/IdeaProjects");
            Path file = (Path) Files.find(directory, 1, (path, basicFileAttributes) -> path.getFileName().toString().equals("notes_" + userID + ".txt"));
            return file;
        }

        public static ArrayList<Note> getInfoFromFile(int userID) throws IOException, ClassNotFoundException {
            Path file = FilesNotes.FindFile(userID);
            FileInputStream fileInputStream = new FileInputStream(file.toFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Note> notes = (ArrayList<Note>) objectInputStream.readObject();
            fileInputStream.close();
            return notes;
        }

        public static void AddNoteInFile(int userID, UUID id) throws IOException, ClassNotFoundException {
            ArrayList<Note> note = FilesNotes.getInfoFromFile(userID);
            note.add(NotesController.findInNoteListbyID(id));
            Path file = FilesNotes.FindFile(userID);
            try (ObjectOutputStream sendInFile = new ObjectOutputStream((OutputStream) file)) {
                sendInFile.writeObject(note);
                sendInFile.close();
            }
            ((OutputStream) file).close();
        }

        public String addEmailToProfilesFile(String email) {

            return email;
        }

        public String FindEmailInProfilesFile(String email) {

            return email;
        }
    }

