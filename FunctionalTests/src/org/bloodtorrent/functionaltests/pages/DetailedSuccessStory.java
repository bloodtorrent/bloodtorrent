package org.bloodtorrent.functionaltests.pages;

import net.sf.sahi.client.Browser;

public class DetailedSuccessStory extends BasePage {

	public DetailedSuccessStory(Browser browser) {
		super(browser);
	}

	public boolean getTitle() {
		return browser.heading1(3).exists();
	}

	public void gotoDetailSuccessStory() {
		browser.link("Home").click();
	}
}
