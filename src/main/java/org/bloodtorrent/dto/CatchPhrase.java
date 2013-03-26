package org.bloodtorrent.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/26/13
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "CATCH_PHRASE")
@Getter @Setter
@SuppressWarnings("PMD.UnusedPrivateField")
public class CatchPhrase {
    @Id
    @NotBlank
    private String id;

    @NotBlank
    @Pattern(regexp = "^(\\/\\w+)+\\.(png|gif|jpg|jpeg)$")
    @Column(name = "IMAGE_PATH")
    private String imagePath;
}
