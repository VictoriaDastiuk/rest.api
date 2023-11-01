package com.myNotes.rest.api.services;

import ch.qos.logback.core.net.AutoFlushingObjectWriter;
import com.myNotes.rest.api.model.Note;
import com.myNotes.rest.api.model.Profile;
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

    public static String[] FindAndReturnInfoFromFile (String nameFile) throws IOException {
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
                    }
                    else {
                        return null;
                    }
                }
            }

        }catch (IOException e){
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
    public void AddNoteInFile(List<Note> notes) throws IOException, ClassNotFoundException {
        File file = new File("Notes.txt");
        FileWriter writer = new FileWriter(file, Charset.forName("UTF-8"));

        // Записати профілі у файл, розділені комою
        for (Note note : notes) {
            writer.write(note.getNameNote() + " " + note.getTitleNote() + " " + note.getTextNote() + " " + note.getAddDate() + " " + note.getModifyDate() + " " + note.getStatusNote() + " " + note.getId() + " " + note.getUserID() + "\n");
        }
        writer.close();
    }
}
//
//    public static void NewNotesFile(int userID) throws IOException {
//        FileOutputStream newFile = new FileOutputStream("notes_" + userID + ".txt");
//    }
//
//    public static File FindFileNotes(int userID) throws IOException {
//        File directory = new File("/home/DN180996DVA/IdeaProjects/rest.api");
//
//        File file = new File(directory, "notes_" + userID + ".txt");
//
//        if (file.exists()) {
//            return file;
//        } else {
//            return null;
//        }
//    }
//
//    public static List<Note> getInfoFromNotesFile(File file) throws IOException, ClassNotFoundException {
//        FileInputStream fileInputStream = new FileInputStream(file);
//        try {
//            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//            List<Note> notes = (List<Note>) objectInputStream.readObject();
//            fileInputStream.close();
//            return notes;
//        } catch (IOException e) {
//            return null;
//        }
//    }
//
//    public static List<Note> returnNotesFromFile(int userID) throws IOException, ClassNotFoundException {
//        File file = FilesNotes.FindFileNotes(userID);
//
//        if (file == null) {
//            FilesNotes.NewNotesFile(userID);
//            file = FilesNotes.FindFileNotes(userID);
//            return null;
//        }
//        return FilesNotes.getInfoFromNotesFile(file);
//    }


//        Note note = NotesContr.findInNoteListbyID(id, userID);
//        if (infoFromFile == null) {
//            infoFromFile.add(note);
//        }
//
//        // Створити об'єкт ObjectOutputStream
//        FileOutputStream fileOutputStream = new FileOutputStream("notes_" + userID + ".txt");
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//        objectOutputStream.writeObject(infoFromFile);
//
//        // Закрити об'єкти
//        objectOutputStream.close();
//        fileOutputStream.close();
//
//    }