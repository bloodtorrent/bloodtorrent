package org.bloodtorrent.functionaltests.scenarios;

// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertEquals;
import net.sf.sahi.client.Browser;

import org.bloodtorrent.functionaltests.pages.SuccessStoryList;
import static junit.framework.Assert.*;

public class OnSuccessStoryListPage {

	private SuccessStoryList successStoryList;

	public OnSuccessStoryListPage(Browser browser) {
		successStoryList = new SuccessStoryList(browser);
	}

	public void verifyTitleOfSuccessStoryList(String successStoryTitle) throws Exception {
		assertEquals(successStoryTitle, successStoryList.getTitleValue());
	}
}
