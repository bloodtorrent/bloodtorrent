package org.bloodtorrent.view;

import com.yammer.dropwizard.views.View;
import lombok.Getter;
import lombok.Setter;
import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.dto.User;

import java.util.List;

@SuppressWarnings("PMD.UnusedPrivateField")
public class SuccessStoryView extends View {

    private static final String PATH = "/ftl/successStory.ftl";
    public static final String PATH_SUCCESS_STORY_LIST = "/ftl/successStoryList.ftl";
    private static final String PATH_SUCCESS_STORY_VIEW = "/ftl/successStoryEditor.ftl";

    private boolean savedSuccessFlag;
    @Getter
	private SuccessStory successStory;

    @Getter @Setter
    public List<SuccessStory> successStoryList;

    @Getter @Setter
    private User user;

    public void setSavedSuccessFlag(Boolean flag) {
        savedSuccessFlag = flag.booleanValue();
    }

    public boolean getSavedSuccessFlag() {
        return savedSuccessFlag;
    }

    public SuccessStoryView(SuccessStory successStory) {
		super(PATH);
		this.successStory = successStory;
	}

    public SuccessStoryView(List<SuccessStory> successStoryList) {
        super(PATH_SUCCESS_STORY_LIST);
        this.successStoryList = successStoryList;
    }

    public SuccessStoryView() {
        super(PATH_SUCCESS_STORY_VIEW);
    }
}

