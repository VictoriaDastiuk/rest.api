package com.myNotes.rest.api.controller;

import com.myNotes.rest.api.model.AuthDto;
import com.myNotes.rest.api.model.Note;
import com.myNotes.rest.api.model.NoteDto;
import com.myNotes.rest.api.model.Profile;
import com.myNotes.rest.api.services.Auth;
import com.myNotes.rest.api.services.NoteList;
import com.myNotes.rest.api.services.NotesController;
import com.myNotes.rest.api.services.ProfilesController;
import org.springframework.beans.factory.annotation.Autowired;
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
    String result;
    @Autowired
    NotesController notesControl;
    @Autowired
    ProfilesController profilesContr;
    @Autowired
    Auth authControl;

    @RequestMapping(value = "/api/auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String Auth(@RequestBody AuthDto authDto) throws IOException, ClassNotFoundException {
        if (authDto.getEmail().isEmpty() || authDto.getName().isEmpty()) {
            return "Message: error. Missing parametr";
        } else {
            result = authControl.startAuth(authDto.getEmail(), authDto.getName());
            return result;
        }
    }

    @RequestMapping(value = "/api/newNote", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String newNote(@RequestBody NoteDto noteDto) throws IOException, ClassNotFoundException {
        if (noteDto.getEmail().isEmpty() || noteDto.getNameNote().isEmpty()) {
            return "Message: error. Missing parametr for email or name fo Note";
        } else {
            result = notesControl.makeNote(noteDto.getNameNote(), noteDto.getTitle(), noteDto.getText(), noteDto.getEmail());
            return result;
        }
    }

    @RequestMapping(value = "/api/changeNote", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String changeNote(@RequestBody NoteDto noteDto) throws IOException, ClassNotFoundException {
        if (noteDto.getEmail().isEmpty() || noteDto.getValueParamFind().isEmpty() || noteDto.getHowFind().isEmpty()) {
            return "Message: error. Missing parametr for email or name fo Note";
        } else {
            notesControl.WantChangeNote(noteDto.getEmail(), noteDto.getNameNote(),noteDto.getTitle(),noteDto.getText(),noteDto.getHowFind(),noteDto.getValueParamFind());
            return result;
        }
    }

    @RequestMapping(value = "/api/showAllNotes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Note> showAllNotes(@RequestParam(required = true) String email) throws IOException, ClassNotFoundException {
        return notesControl.ShowNoteList(email);
    }

    @RequestMapping(value = "/api/showNote", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String showNote(@RequestBody NoteDto noteDto) throws IOException, ClassNotFoundException {
        if (noteDto.getEmail().isEmpty() || noteDto.getValueParamFind().isEmpty() || noteDto.getHowFind().isEmpty()) {
            return "Message: error. Missing parametr for email or name fo Note";
        } else {
            notesControl.WantShowNote(noteDto.getEmail(),noteDto.getHowFind(),noteDto.getValueParamFind());
            return result;
        }
    }

    @RequestMapping(value = "/api/delNote", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String delNote(@RequestBody NoteDto noteDto) throws IOException, ClassNotFoundException {
        if (noteDto.getEmail().isEmpty() || noteDto.getValueParamFind().isEmpty() || noteDto.getHowFind().isEmpty()) {
            return "Message: error. Missing parametr for email or name fo Note";
        } else {
            notesControl.deleteNote(noteDto.getHowFind(),noteDto.getValueParamFind(),noteDto.getEmail());
            return result;
        }
    }

    @RequestMapping(value = "/api/showAllProfiles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Profile> showAllProfiles() throws IOException, ClassNotFoundException {
        return profilesContr.ShowProfileList();
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

