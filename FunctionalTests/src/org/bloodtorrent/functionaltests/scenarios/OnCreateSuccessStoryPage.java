package org.bloodtorrent.functionaltests.scenarios;

import net.sf.sahi.client.Browser;

import org.bloodtorrent.functionaltests.pages.CreateSuccessStory;

public class OnCreateSuccessStoryPage {

    private CreateSuccessStory createSuccessStory;

	private String title = "Success Story!";
	private String summary = "Saved my life!";
	private String description = "Saved my life! Desc....";
	private String imgPathForWin = "..\\..\\bin\\test_images\\ss_test01.png";
	private String imgPathForLinux = "../../bin/test_images/ss_test01.png";

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
		String currentOS = System.getProperty("os.name");
		if (currentOS.contains("Windows")){
			createSuccessStory.setImageFile(imgPathForWin);
		}else{
			createSuccessStory.setImageFile(imgPathForLinux);
		}
	}

	public void saveSuccessStory() throws Exception {
		createSuccessStory.saveSuccessStory();
	}

}
