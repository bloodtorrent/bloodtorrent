package org.bloodtorrent.functionaltests.scenarios;

import net.sf.sahi.client.Browser;
import org.bloodtorrent.functionaltests.pages.DetailedSuccessStory;
import static junit.framework.Assert.*;

public class OnDetailedSuccessStoryPage {

	private DetailedSuccessStory detailedSuccessStory;
	
	public OnDetailedSuccessStoryPage(Browser browser) {
		detailedSuccessStory = new DetailedSuccessStory(browser);
	}

	public void verifyDetailedSuccessStoryTitle() throws Exception {
		assertTrue(detailedSuccessStory.getTitle().exists());
	}

	public void goBackHome() throws Exception {
		detailedSuccessStory.getHomeLink().click();
	}
}
