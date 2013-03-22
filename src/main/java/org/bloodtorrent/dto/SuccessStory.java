package org.bloodtorrent.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter @Setter
public class SuccessStory {
    @NotBlank
    private String title;
    @NotBlank
    private String story;
}
