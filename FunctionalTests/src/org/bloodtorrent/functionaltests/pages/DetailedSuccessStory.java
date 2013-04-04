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

	public void checkList(int chkNum) {
		for(int i = 0; i < chkNum; i++) {
			String postfix = ((i==0) ? "":"["+i+"]");
			browser.checkbox("checkStoryId"+postfix).click();
		}
	}

	public void displayOnTheMainPage() {
		browser.button("Display on the main page").click();	
	}

	public String getMessage() {
		return browser.lastAlert();
	}

}
