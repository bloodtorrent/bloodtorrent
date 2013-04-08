package org.bloodtorrent.functionaltests.pages;

import net.sf.sahi.client.Browser;
import net.sf.sahi.client.ElementStub;

public class SuccessStoryList extends BasePage {

	public SuccessStoryList(Browser browser) {
		super(browser);
	}
	
	public ElementStub getTitleElement() {
		return browser.heading1("title");
	}
	
	public String getTitleValue() {
		return getTitleElement().text();
	}

}
