package org.bloodtorrent.functionaltests.scenarios;

// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnSuccessStoryListPage {

	private Browser browser;

	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public OnSuccessStoryListPage(Browser browser) {
		this.browser = browser;
	}

	public void createSuccessStory() throws Exception {
	
	}

	public void verifySuccessStoryTitle() throws Exception {
	
	}

}
