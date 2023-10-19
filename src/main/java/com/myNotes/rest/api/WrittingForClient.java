package com.myNotes.rest.api;

import java.util.Scanner;
import java.util.UUID;

public class WrittingForClient {
    static Scanner scanner;

    public static void whatChange() {
         System.out.println("Що бажаєте змінити в нотатці: назву, заголовок чи текст?");

        }

        public static void addNew (String whatChange){
        if (whatChange.equals("назву")) {
            System.out.println("Введіть нову назву для нотатки");
        }

        if (whatChange.equals("заголовок")) {
            System.out.println("Введіть новий заголовок для нотатки");
        }

        if (whatChange.equals("текст")) {
            System.out.println("Введіть новий текст для нотатки");
        }
    }
    public static void printNameNote() {
        System.out.println("Ввведіть назву для нотатки: ");

    }
    public static void printTitleNote() {
        System.out.println("Ввведіть заголовок для нотатки: ");

    }
    public static void printTextNote() {
        System.out.println("Ввведіть,текст нотатки: ");

    }

    public static void printYouHaveAccount() {
        System.out.println("Ви вже зареєстровані?");
    }
    public static void printName() {
        System.out.println("Введіть своє ім'я: ");
    }

    public static void printMAil() {
        System.out.println("Введіть свій емейл: ");

    }

    public static void whatToDO() {
        System.out.println("Напишіть що бажаєте зробити:" +
                "1 - створити нову" +
                "2 - змінити стару " +
                "3 - переглянути список нотаток" +
                "4 - переглянути нотатку" +
                "5 - видалити нотатку?");
    }
    public static void howFind() {
        System.out.println("По чому будемо шукати нотатку щоб змінити: назва чи заголовок?");
        String howFind = scanner.nextLine();
    }
    public static void writeParam() {
        String howFind = scanner.nextLine();
        //пошук по заголовку
        if (howFind.equals("заголовок")) {
            System.out.println("Введіть заголовок нотатки: ");
        }

        //пошук по назві
        if (howFind.equals("назва")) {
            System.out.println("Введіть назву нотатки: ");
        }
    }

    public static void dontFind() {
        System.out.println("Не найшли нотатку");
    }

    public static void DoSmthMore() {
        System.out.println("Бажаєте ще якусь дію здійснити з нотатками?");
    }
    public static void printProfileMade() {
        System.out.println("Профіль створено");
    }
    public static void printProfileDontMade() {
        System.out.println("Профіль не створено, спробуйте пізніше");
    }

    public static void showNote(UUID resultOfFindNote) {
        Note note  = NotesController.findInNoteListbyID(resultOfFindNote);
        //показуємо текст нотатки
        assert note != null;
        if (note.getStatusNote().equals("Created") || note.getStatusNote().equals("Modified"))
        {
            System.out.println(note.getNameNote() + ": " + note.getTitleNote() + " Текст нотатки:" + note.getTextNote());
        }
        else {
            System.out.println("У вас немає такої нотатки або ж вона вже видалена");
        }
    }

}
