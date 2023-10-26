package com.myNotes.rest.api.services;

import com.myNotes.rest.api.model.Note;
import com.myNotes.rest.api.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.UUID;

//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.usermodel.XSSFWorkbook;
@Service
public class FilesNotes {
    File folder = new File("./Notes");
    @Autowired
    NotesController NotesContr;


    public static void NewProfileFile() throws IOException {
        FileOutputStream newFile = new FileOutputStream("Profiles.txt");
    }

    public static File FindFileProfiles() throws IOException {
        File dir = new File("/home/DN180996DVA/IdeaProjects/rest.api");
        String name = "Profiles.txt";
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.getName().equals(name)) {
                return file;
            }
        }
        return null;
    }

    public static List<Profile> getInfoProfilesFile() throws IOException, ClassNotFoundException {
        File file = FilesNotes.FindFileProfiles();
        if (file != null) {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                List<Profile> profiles = (List<Profile>) objectInputStream.readObject();
                fileInputStream.close();
                return profiles;
            } catch (IOException e) {
                return null;
            }
        }
        return null;

    }

    public static void AddProfileInFile(List<Profile> infoFromFile) throws IOException, ClassNotFoundException {

        // Створити об'єкт ObjectOutputStream
        FileOutputStream fileOutputStream = new FileOutputStream("Profiles.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(infoFromFile);

        // Закрити об'єкти
        objectOutputStream.close();
        fileOutputStream.close();

    }

    public static void NewNotesFile(int userID) throws IOException {
        FileOutputStream newFile = new FileOutputStream("notes_" + userID + ".txt");
    }

    public static File FindFileNotes(int userID) throws IOException {
        File directory = new File("/home/DN180996DVA/IdeaProjects/rest.api");

        File file = new File(directory, "notes_" + userID + ".txt");

        if (file.exists()) {
            return file;
        } else {
            return null;
        }
    }

    public static List<Note> getInfoFromNotesFile(File file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<Note> notes = (List<Note>) objectInputStream.readObject();
            fileInputStream.close();
            return notes;
        } catch (IOException e) {
            return null;
        }
    }

    public static List<Note> returnNotesFromFile(int userID) throws IOException, ClassNotFoundException {
        File file = FilesNotes.FindFileNotes(userID);

        if (file == null) {
            FilesNotes.NewNotesFile(userID);
            file = FilesNotes.FindFileNotes(userID);
            return null;
        }
        return FilesNotes.getInfoFromNotesFile(file);
    }

    public void AddNoteInFile(int userID, UUID id, List<Note> infoFromFile) throws IOException, ClassNotFoundException {
        Note note = NotesContr.findInNoteListbyID(id, userID);
        if (infoFromFile == null) {
            infoFromFile.add(note);
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

