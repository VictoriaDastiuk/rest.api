package com.myNotes.rest.api;

import java.util.UUID;

import static com.myNotes.rest.api.ModelOfProject.scanner;

public class ChangeNote {

    public static void WantChangeNote(int userID) {
//        питаєм по чому робим пошук нотатки? і виводить айді нотатки
        UUID resultOfFindNote = NotesController.preFindNote();

        if (resultOfFindNote == null) {
            //якшо не нашли нотатку
            WrittingForClient.dontFind();
        } else {
            // питаєм шо міняти в нотатці
            WrittingForClient.whatChange();
            String whatChange = scanner.nextLine();

            //введення нового значення
            WrittingForClient.addNew(whatChange);
            String param = scanner.nextLine();


            //resultOfFindNote - ID Note
            //param - нове значення параметру який міняєм
            //whatChange - що треба змінити в нотатці
            NoteList.changeNote(resultOfFindNote, param, whatChange);

            System.out.println("Нотатка успішно змінена.");
            break;
        }
    }

}
