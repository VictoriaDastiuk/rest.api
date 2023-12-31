package com.myNotes.rest.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDto {
    @JsonProperty("email")
    String email;
    @JsonProperty("name")
    String name;
}
