package org.bloodtorrent.view;

import com.yammer.dropwizard.views.View;
import lombok.Getter;
import lombok.Setter;
import org.bloodtorrent.dto.SuccessStory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/25/13
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class SuccessStoryView extends View {

    private static final String PATH = "/ftl/successStory.ftl";
    public static final String PATH_SUCCESS_STORY_LIST = "/ftl/successStoryList.ftl";
    private static final String PATH_SUCCESS_STORY_VIEW = "/ftl/successStoryEditor.ftl";

    @Getter
	private SuccessStory successStory;

    @Getter @Setter
    public List<SuccessStory> successStoryList;


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

