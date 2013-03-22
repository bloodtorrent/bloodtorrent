package org.bloodtorrent.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter @Setter
@SuppressWarnings("PMD.UnusedPrivateField")
public class SuccessStory {
    @NotBlank
    private String title;
    @NotBlank
    private String story;
}
