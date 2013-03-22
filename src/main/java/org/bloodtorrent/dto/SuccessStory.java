package org.bloodtorrent.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

<<<<<<< HEAD
@Getter @Setter
@SuppressWarnings("PMD.UnusedPrivateField")
=======
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 22
 * Time: 오전 11:26
 * To change this template use File | Settings | File Templates.
 */
@Entity(name = "SUCCESS_STORY")
>>>>>>> [John/Inchul] #57 Login as an Administrator using servelt session
public class SuccessStory {
    @Id
    @NotBlank
    private String title;
    @NotBlank
    private String story;
}
