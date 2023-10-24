package com.myNotes.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.*;

@Controller
@RestController
public class ModelOfProject {
    static Scanner scanner = new Scanner(System.in);
    String doOther;
    String answer;
    String valueParamFind;
    String howFind;
    UUID resultOfFindNote;
    String result;
    int userID;
    NotesController notesControl = NotesController.getInstance();
    ProfilesController profilesContr = ProfilesController.getInstance();
    NoteList noteListControl = NoteList.getInstance();
    Auth authControl = Auth.getInstance();


    String whatChange;
    boolean  checkAuth;
    @RequestMapping(value = "/api/auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String Auth(@RequestParam("name") String name, @RequestParam("email") String email) throws IOException, ClassNotFoundException {
        result = authControl.startAuth(email,name);
        return result;
        }

    @RequestMapping(value = "/api/newNote", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String newNote(@RequestParam(required = true) String email,@RequestParam(required = true) String nameNote, @RequestParam(required = true) String title, @RequestParam(required = true) String text) throws IOException, ClassNotFoundException {
        result = notesControl.makeNote(nameNote, title, text, email);
        return result;
    }

    @RequestMapping(value = "/api/changeNote", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String changeNote(@RequestParam(required = true) String email, @RequestParam(required = true) String nameNote, @RequestParam(required = true) String title, @RequestParam(required = true) String text, @RequestParam(required = true) String howFind, @RequestParam(required = true) String valueParamFind) throws IOException, ClassNotFoundException {
        notesControl.WantChangeNote(email,nameNote,title,text,howFind,valueParamFind);
        return result;
    }
    @RequestMapping(value = "/api/showAllNotes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Note> showAllNotes(@RequestParam(required = true) String email) throws IOException, ClassNotFoundException {
        return notesControl.ShowNoteList(email);
    }
    @RequestMapping(value = "/api/showNote", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Note showNote(@RequestParam(required = true) String email, @RequestParam(required = true) String howFind, @RequestParam(required = true) String valueParamFind) throws IOException, ClassNotFoundException {
        return notesControl.WantShowNote(email,howFind,valueParamFind);
    }
    @RequestMapping(value = "/api/delNote", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String delNote(@RequestParam(required = true)  String email, @RequestParam(required = true)  String howFind, @RequestParam(required = true)  String valueParamFind) throws IOException, ClassNotFoundException {
        return notesControl.deleteNote(howFind,valueParamFind,email);
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> handleAuthenticationError() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized.");
    }
}
    






















    //починається авторизація





        // Що хоче зробити створити нову, змінити стару, переглянути список нотаток, видалити?
//        WrittingForClient.whatToDO();
//        int answerWhatToDo = Integer.parseInt(scanner.nextLine());
//        int userID = profilesContr.findInProfileList(email);

//
//            switch (answerWhatToDo) {
//
//                // СТВОРИТИ НОТАТКУ
//                case 1:
//                    WrittingForClient.printNameNote();
//                    String nameNote = scanner.nextLine();
//                    WrittingForClient.printTitleNote();
//                    String title = scanner.nextLine();
//                    WrittingForClient.printTextNote();
//                    String text = scanner.nextLine();
//                    break;

                    //ЗМІНИТИ НОТАТКУ
//                case 2:
//                    WrittingForClient.howFind();
//                    howFind = scanner.nextLine();
//                    WrittingForClient.writeParam();
//                    valueParamFind = scanner.nextLine();
//                    WrittingForClient.printNameNote();
//                    nameNote = scanner.nextLine();
//                    WrittingForClient.printTitleNote();
//                    title = scanner.nextLine();
//                    WrittingForClient.printTextNote();
//                    text = scanner.nextLine();
//                    break;
//
//                    //ПЕРЕГЛЯНУТИ ВСІ НОТАТКИ
//                case 3:
//                    break;
//
//                //ПЕРЕГЛЯНУТИ НОТАТКУ
//                case 4:
//                    WrittingForClient.howFind();
//                    howFind = scanner.nextLine();
//                    WrittingForClient.writeParam();
//                    valueParamFind = scanner.nextLine();
//                    break;

//                //ВИДАЛИТИ НОТАТКУ
//                case 5:
//                    WrittingForClient.howFind();
//                    howFind = scanner.nextLine();
//                    WrittingForClient.writeParam();
//                    valueParamFind = scanner.nextLine();
//                    break;
//            }
//}

