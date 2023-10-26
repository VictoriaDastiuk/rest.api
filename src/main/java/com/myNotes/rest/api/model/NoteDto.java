package com.myNotes.rest.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

    @Getter
    @Setter
    public class NoteDto {
        @JsonProperty("email")
        String email;
        @JsonProperty("nameNote")
        String nameNote;
        @JsonProperty("title")
        String title;
        @JsonProperty("text")
        String text;
        @JsonProperty("howFind")
        String howFind;
        @JsonProperty("valueParamFind")
        String valueParamFind;

    }


