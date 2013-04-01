package org.bloodtorrent.functionaltests.scenarios;

// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

import org.bloodtorrent.functionaltests.pages.CreateSuccessStory;

public class OnCreateSuccessStoryPage {

    private CreateSuccessStory createSuccessStory;

	private String title = "Success Story!";
	private String summary = "Saved my life!";
	private String description = "Saved my life! Desc....";
	private String imgPath = "..\\..\\..\\src\\main\\resources\\images\\ss_test01.png";

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

	public void registerSuccessStory() throws Exception {
		createSuccessStory.save();
	}

}
