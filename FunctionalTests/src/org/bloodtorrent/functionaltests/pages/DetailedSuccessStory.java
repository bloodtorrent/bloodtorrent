package org.bloodtorrent.functionaltests.pages;

import net.sf.sahi.client.Browser;
import net.sf.sahi.client.ElementStub;

public class DetailedSuccessStory extends BasePage {

	public DetailedSuccessStory(Browser browser) {
		super(browser);
	}

	public ElementStub getTitle() {
		return browser.byId("title");
	}

	public ElementStub getHomeLink() {
		return browser.link("Home");
	}

}
