package org.bloodtorrent.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "SUCCESS_STORY")
@Getter @Setter @NoArgsConstructor
@SuppressWarnings("PMD.UnusedPrivateField,PMD.UnnecessaryConstructor")
public class SuccessStory {
    public static final int MAX_TITLE_LENGTH = 150;
    public static final int MAX_SUMMARY_LENGTH = 250;
	public static final int MAXIMUM_SHOWN_STORY_ON_MAIN_PAGE = 3;
    private static final String REGEXP_YN = "^Y|N$";

    @Id
	private String id;

	@NotBlank(message = "Please fill out title.")
    @Size(max = MAX_TITLE_LENGTH, message = "Title is too long.")
	private String title;

	@Column(name = "THUMBNAIL_PATH")
	private String thumbnailPath;

	@Column(name = "VISUAL_RESOURCE_PATH")
	private String visualResourcePath;

    @Size(max = MAX_SUMMARY_LENGTH, message = "Short Description is too long.")
    private String summary;

	private String description;

	@Pattern(regexp = REGEXP_YN)
	@Column(name = "SHOW_MAIN_PAGE")
	private String showMainPage;

	@Column(name = "CREATE_DATE")
	private Date createDate = new Date();

    public SuccessStory(String id, String title, String summary, String description, String showMainPage) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.description = description;
        this.showMainPage = showMainPage;
    }
}
