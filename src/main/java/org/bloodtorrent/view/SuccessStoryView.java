package org.bloodtorrent.view;

import com.yammer.dropwizard.views.View;
import lombok.Getter;
import org.bloodtorrent.dto.SuccessStory;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/25/13
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class SuccessStoryView extends View {

	private static final String PATH = "/ftl/successStory.ftl";

	@Getter
	private SuccessStory successStory;

	public SuccessStoryView(SuccessStory successStory) {
		super(PATH);
		this.successStory = successStory;
	}
}

