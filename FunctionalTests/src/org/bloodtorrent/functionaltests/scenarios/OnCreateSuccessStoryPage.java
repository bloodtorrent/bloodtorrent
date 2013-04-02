package org.bloodtorrent.functionaltests.scenarios;

import net.sf.sahi.client.Browser;

import org.bloodtorrent.functionaltests.pages.CreateSuccessStory;
import static java.io.File.separator;

public class OnCreateSuccessStoryPage {

    private CreateSuccessStory createSuccessStory;

	private String title = "Success Story!";
	private String summary = "Saved my life!";
	private String description = "Saved my life! Desc....";
	private String imgPath = ".." + separator + ".." + separator + "bin" + separator + "test_images" + separator + "ss_test01.png";

	public OnCreateSuccessStoryPage(Browser browser) {
		createSuccessStory = new CreateSuccessStory(browser);
	}

	public void cancelCreatingASuccessStory() throws Exception {
		createSuccessStory.cancel();
	}

	public void fillOutTheInformationToRegisterSuccessStory() throws Exception {
		createSuccessStory.setTitle(title);
		createSuccessStory.setSummary(summary);
		createSuccessStory.setDescription(description);
	}

	public void attachAnImage() throws Exception {
		createSuccessStory.setImageFile(imgPath);
	}

	public void saveSuccessStory() throws Exception {
		createSuccessStory.saveSuccessStory();
	}

}
