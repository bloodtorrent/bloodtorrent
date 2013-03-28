package org.bloodtorrent.functionaltests.scenarios;

// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

import org.bloodtorrent.functionaltests.pages.DetailedSuccessStory;
import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;
import static junit.framework.Assert.*;

public class OnDetailedSuccessStoryPage {

	private Browser browser;
	private DetailedSuccessStory detailedSuccessStory;
	
	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public OnDetailedSuccessStoryPage(Browser browser) {
		detailedSuccessStory = new DetailedSuccessStory(browser);
	}

	public void verifyDetailedSuccessStoryTitle() throws Exception {
		assertTrue(detailedSuccessStory.getTitle());
	}

	public void goBackHome() throws Exception {
		detailedSuccessStory.gotoDetailSuccessStory();
	}
}
