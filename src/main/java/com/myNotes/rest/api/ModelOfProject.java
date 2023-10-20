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
    static String doOther;
    static String answer;
    static String valueParamFind;
    static String howFind;
    static UUID resultOfFindNote;
    static int userID;

    static String whatChange;
    static boolean  checkAuth;

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
        WrittingForClient.howFind();
        String howFind = scanner.nextLine();
        WrittingForClient.writeParam();
        String valueParamFind = scanner.nextLine();


            switch (answerWhatToDo) {

                // СТВОРИТИ НОТАТКУ
                case 1:
                    notesControl.makeNote(nameNote,title,text,email);
                    break;

                    //ЗМІНИТИ НОТАТКУ
                case 2:
                    notesControl.WantChangeNote(nameNote,title,text,email,howFind,valueParamFind);
                    break;

                    //ПЕРЕГЛЯНУТИ ВСІ НОТАТКИ
                case 3:
                    notesControl.ShowNoteList(email);
                    break;

                //ПЕРЕГЛЯНУТИ НОТАТКУ
                case 4:
                    notesControl.WantShowNote(email,howFind,valueParamFind);
                    break;

                //ВИДАЛИТИ НОТАТКУ
                case 5:
                    notesControl.deleteNote(howFind,valueParamFind,email);
                    break;
            }
}
}

