package org.bloodtorrent.functionaltests.scenarios;

import net.sf.sahi.client.Browser;
import org.bloodtorrent.functionaltests.pages.DetailedSuccessStory;

import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;
import static junit.framework.Assert.*;

public class OnDetailedSuccessStoryPage {

	private DetailedSuccessStory detailedSuccessStory;
	
	public OnDetailedSuccessStoryPage(Browser browser) {
		detailedSuccessStory = new DetailedSuccessStory(browser);
	}

	public void verifyDetailedSuccessStoryTitle() throws Exception {
		assertTrue(detailedSuccessStory.getTitle().exists());
	}

	public void goBackToHomePage() throws Exception {
		detailedSuccessStory.getHomeLink().click();
	}

	public void checkStories(int chkNum) throws Exception {
		detailedSuccessStory.checkList(chkNum);
	}

	public void displayOnTheHomePage() throws Exception {
		detailedSuccessStory.displayOnTheMainPage();
	}

	public void verifyTheMessageInCaseThatMoreStorySelection() throws Exception {
		verifyEquals("You can choose 3 success stories at most", detailedSuccessStory.getMessage());
	}

	public void verifyTheMessageInCaseThatWithoutStorySelection() throws Exception {
		verifyEquals("Please, select 1 ~ 3 stories for displaying on the main page", detailedSuccessStory.getMessage());
	}

	public void verifyTheMessageInCaseThatStorySelectionSuccessfully()
			throws Exception {
		verifyEquals("These stories will be displayed as success story on the main page", detailedSuccessStory.getMessage());
	}


}
