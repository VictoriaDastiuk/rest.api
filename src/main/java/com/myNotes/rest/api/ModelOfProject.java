package com.myNotes.rest.api;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class ModelOfProject {
    static Scanner scanner;
    static String doOther;
    static String answer;
    static String paramToFind;
    static String howFind;
    static UUID resultOfFindNote;
    static int userID;

    static String whatChange;
    static boolean  checkEmail;

    public static void MyNotes() throws IOException, ClassNotFoundException {
        //починається авторизація
        Auth ans = new Auth();
        answer = ans.startAuth();

        //має аккаунт чи ні
        if (answer.equals("нет") || answer.equals("ні")) {
            checkEmail = ans.AuthForNewCustom();
        }
        else {
           checkEmail = ans.AuthForOldCustom();
        }


        //ця штука треба якшо вже такий емейл є при новій реєстрації чи якшо ненашли користувача по емейлу
        if (!checkEmail) {
            Auth.rePrintEmail(answer);
        }


        //ідем далі якшо все по емейлу ок
        else {
        do {
            WrittingForClient.whatToDO();
            // Що хоче зробити створити нову, змінити стару, переглянути список нотаток, видалити?
            int answerWhatToDo = Integer.parseInt(scanner.nextLine());
            userID = Profile.userID;

            switch (answerWhatToDo) {

                // СТВОРИТИ НОТАТКУ
                case 1:
                    MakeNote.makeNote(userID);
                    break;

                    //ЗМІНИТИ НОТАТКУ
                case 2:
                    ChangeNote.WantChangeNote(userID);
                    break;

                    //ПЕРЕГЛЯНУТИ ВСІ НОТАТКИ
                case 3:
                    ShowNoteList.ShowNoteList(userID);
                    break;

                //ПЕРЕГЛЯНУТИ НОТАТКУ
                case 4:
                    ShowNote.WantShowNote(userID);
                    break;

                //ВИДАЛИТИ НОТАТКУ
                case 5:
                   DeleteNote.deleteNote(userID);
                    break;

                //ВИВАНТАЖИТИ НОТАТКИ
                case 6:

                    break;

            }
// Бажаєте ще якусь дію здійснити?
            WrittingForClient.DoSmthMore();
            String doOther = scanner.nextLine();
            if ((doOther.equals("ні")) || (doOther.equals("нет"))) {
                System.out.println("Thanks for using app!");
                break;
            }
        } while (doOther.equals("так") || doOther.equals("да"));
    }
}}

