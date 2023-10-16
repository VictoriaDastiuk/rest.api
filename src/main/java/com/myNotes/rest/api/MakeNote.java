package com.myNotes.rest.api;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class MakeNote {
    static Scanner scanner = new Scanner(System.in);
public static void makeNote(int userID) throws IOException, ClassNotFoundException {
    //  визначення сьогоднішньої дати
    String formattedDate = NotesController.getDate();

    // введіть назву, текст, заголовок
    WrittingForClient.printNameNote();
    String name = scanner.nextLine();
    WrittingForClient.printTitleNote();
    String title = scanner.nextLine();
    WrittingForClient.printTextNote();
    String text = scanner.nextLine();

    Note note = new Note(userID);
    UUID ID = note.getId();
    NotesController.changeAllNote(ID,title,name,text);

    // додавання в файл нотатки
    FilesNotes.AddNoteInFile(userID, ID);
    }
}
