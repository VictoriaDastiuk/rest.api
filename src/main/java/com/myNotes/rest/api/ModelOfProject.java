package com.myNotes.rest.api;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
@Controller
public class ModelOfProject {
    static Scanner scanner = new Scanner(System.in);
    String doOther;
    String answer;
    static String valueParamFind;
    static String howFind;
    UUID resultOfFindNote;
    int userID;


    String whatChange;
    boolean  checkAuth;

    public static void MyNotes() throws IOException, ClassNotFoundException {

        WrittingForClient.printName();
        String name = scanner.nextLine();
        WrittingForClient.printMAil();
        String email = scanner.nextLine();

        //починається авторизація
        Auth ans = new Auth();
        boolean checkAuth = ans.startAuth(email, name);

        if (checkAuth){
            System.exit(503);
        }

        NotesController notesControl = NotesController.getInstance();

        // Що хоче зробити створити нову, змінити стару, переглянути список нотаток, видалити?
        WrittingForClient.whatToDO();
        int answerWhatToDo = Integer.parseInt(scanner.nextLine());

        WrittingForClient.printNameNote();
        String nameNote = scanner.nextLine();
        WrittingForClient.printTitleNote();
        String title = scanner.nextLine();
        WrittingForClient.printTextNote();
        String text = scanner.nextLine();



            switch (answerWhatToDo) {

                // СТВОРИТИ НОТАТКУ
                case 1:
                    notesControl.makeNote(nameNote,title,text,email);
                    break;

                    //ЗМІНИТИ НОТАТКУ
                case 2:
                    WrittingForClient.howFind();
                    howFind = scanner.nextLine();
                    WrittingForClient.writeParam();
                    valueParamFind = scanner.nextLine();
                    notesControl.WantChangeNote(email,nameNote,title,text,howFind,valueParamFind);
                    break;

                    //ПЕРЕГЛЯНУТИ ВСІ НОТАТКИ
                case 3:
                    notesControl.ShowNoteList(email);
                    break;

                //ПЕРЕГЛЯНУТИ НОТАТКУ
                case 4:
                    WrittingForClient.howFind();
                    howFind = scanner.nextLine();
                    WrittingForClient.writeParam();
                    valueParamFind = scanner.nextLine();
                    notesControl.WantShowNote(email,howFind,valueParamFind);
                    break;

                //ВИДАЛИТИ НОТАТКУ
                case 5:
                    WrittingForClient.howFind();
                    howFind = scanner.nextLine();
                    WrittingForClient.writeParam();
                    valueParamFind = scanner.nextLine();
                    notesControl.deleteNote(howFind,valueParamFind,email);
                    break;
            }
}
}

