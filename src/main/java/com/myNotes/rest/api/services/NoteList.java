package com.myNotes.rest.api.services;

import com.myNotes.rest.api.model.Note;
import com.myNotes.rest.api.model.Profile;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

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
    private void addToListNotesFromFile() throws IOException {
        String NameFile = "Notes.txt";
        String[] listNotes = FilesNotes.FindAndReturnInfoFromFile(NameFile);
        if (listNotes != null) {
            // Створити список об'єктів
            // Перетворити інформацію з файлу на об'єкти
            for (String line : listNotes) {
                // Розбити рядок на поля
                String[] fields = line.split(" ", 11);

                // Створити новий об'єкт
                Note note = new Note();
                note.setNameNote(fields[0]);
                note.setTitleNote(fields[1]);
                note.setTextNote(fields[2]);
                note.setAddDate(fields[3]+fields[4]);
                note.setModifyDate(fields[5]+fields[6]);
                note.setStatusNote(fields[7]);
                note.setId(fields[8]);
                note.setUserID(Integer.parseInt(fields[9]));

                // Додати новий об'єкт до списку
                addNote(note);
            }
        }

    }
}
