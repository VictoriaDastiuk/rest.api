package com.myNotes.rest.api;

import com.myNotes.rest.api.controller.ModelOfProject;

import java.io.IOException;
import java.util.*;

public class Main {
    static String doOther;
    static String answer;
    static String paramToFind;
    static String howFind;
    static UUID resultOfFindNote;
    static String whatChange;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ModelOfProject auth = new ModelOfProject();
        String name = "vika";
        String email = "vika";
        String result = auth.Auth(name,email);
//        List<Profile> profiles = auth.showAllProfiles();

    }
}