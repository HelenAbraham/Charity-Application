package com.nsa.charitystarter.web;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SponsorForm {

    @NotNull
    @Size(min = 2,max = 30, message = "Invalid name")
    private String name;

    @NotNull
    @Size(min = 2,max = 30, message = "Please add action/purpose")
    private String action;

    @NotNull
    @Size(min = 2,max = 30, message = "Please add fURL")
    private String fURL; // TODO: 30/10/2019 find more info about this! Smart URL?

    public String searchable() {
        return String.join(";", name, action, fURL);
    }

}
