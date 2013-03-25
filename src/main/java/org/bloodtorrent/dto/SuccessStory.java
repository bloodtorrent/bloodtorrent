package org.bloodtorrent.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

// TODO [James/Scott] @Entity should be added after creating table within physical db.
@Entity(name = "SUCCESS_STORY")
@Getter @Setter
@SuppressWarnings("PMD.UnusedPrivateField")
public class SuccessStory {
	public static final int MAXIMUM_SHOWN_STORY_ON_MAIN_PAGE = 3;
	private static final String REGEXP_YN = "^Y|N$";

	@Id
	private String id;

	@NotBlank(message = "Title should not be blank.")
	private String title;

	@NotBlank
	@Column(name = "THUMBNAIL_PATH")
	private String thumbnailPath;

	@NotBlank
	@Column(name = "VISUAL_RESOURCE_PATH")
	private String visualResourcePath;

    @NotBlank(message = "Summary should be filled.")
    @Size(max = 100, message = "Summary is too long.")
    private String summary;

	@NotBlank(message = "Description should be filled.")
	private String description;

	@Pattern(regexp = REGEXP_YN)
	@Column(name = "SHOWN_MAIN_PAGE")
	private String shownMainPage;

	@Column(name = "CREATE_DATE")
	private Date createDate;

}
