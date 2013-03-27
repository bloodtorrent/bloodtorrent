package org.bloodtorrent.functionaltests.pages;

import net.sf.sahi.client.Browser;
import net.sf.sahi.client.ElementStub;

public class MainPage extends BasePage {
	public MainPage(Browser browser) {
		super(browser);
	}

	public String getTitle() {
		return browser.byId("title").text();
	}
	
	public void registerDonor() {
		browser.button("Register donor").click();
	}
	
	public void requestForBlood() {
		browser.button("Request blood").click();
	}

	public void enterLoginCredentials(String email, String password) {
		browser.textbox("email").setValue(email);
		browser.password("password").setValue(password);
	}

	public void login() {
		browser.button("Log in").click();
	}

	public ElementStub loginButton() {
		return browser.button("Log in");
	}

	public ElementStub getSectionOfSuccessStory() {
		return browser.byId("successStory");
	}

	public ElementStub getReadMoreLink() {
		return browser.link("successStoryDetailLink");
	}


}
