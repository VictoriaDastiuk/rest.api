package com.myNotes.rest.api;

import java.io.IOException;
import java.util.Scanner;


public class Auth {
    static String answer;
    static Scanner scanner = new Scanner(System.in);
    static boolean checkEmail;

    public String startAuth(){
        WrittingForClient.printYouHaveAccount();
        answer = scanner.nextLine();
        return answer;
    }

    public static boolean AuthForNewCustom() throws IOException {
        //перевірка емейлу раніше використовуваних
        WrittingForClient.printName();
        String user = scanner.nextLine();
        WrittingForClient.printMAil();
        String mail = scanner.nextLine();

        ProfilesController resultMail = new ProfilesController();
        checkEmail = resultMail.checkEmail(mail);
        if (!checkEmail) {
            System.out.println("Емейл такий існує");
        } else {
            // Cтворення користувача
            ProfilesController Id = new ProfilesController();
            int userId = Id.createProfile();
            ProfilesController.changeProfile(userId, user, mail);
            FilesNotes.NewNotesFile(userId);
        }
        return checkEmail;
    }

    public boolean AuthForOldCustom (){
        WrittingForClient.printMAil();
        String mail = scanner.nextLine();

        ProfilesController resultMail = new ProfilesController();
        boolean checkEmail = resultMail.checkEmail(mail);
        if (!checkEmail) {
            System.out.println("Емейл такий не зареєстрований");
        }
        return resultMail.checkEmail(mail);
    }

    public static void rePrintEmail(String answer) throws IOException {
        if (answer.equals("нет") || answer.equals("ні")) {
            boolean checkEmail = Auth.AuthForNewCustom();
        }
        else {
            boolean checkEmail = Auth.AuthForNewCustom();
        }

        if (!checkEmail) {
            System.out.println("Емейл такий існує в системі. Вибачаємо за не зручності, але реєатрацію не може бути проведено з цим емейлом. Гарного дня!");
            System.exit(400);
        }
    }


}
