package com.myNotes.rest.api;

import java.io.IOException;
import java.util.Scanner;


public class Auth {
    static String answer;
    static Scanner scanner = new Scanner(System.in);
    static boolean checkEmail;
    ProfilesController profilesContr = ProfilesController.getInstance();

    public boolean startAuth(String mail, String user) throws IOException {
        checkEmail = profilesContr.checkEmail(mail);
        if (checkEmail) {
            // Cтворення користувача
            int userId = profilesContr.createProfile();
            profilesContr.changeProfile(userId, user, mail);
            FilesNotes.NewNotesFile(userId);
            checkEmail = true;
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



}
