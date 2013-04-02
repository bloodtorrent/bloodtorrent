package org.bloodtorrent.functionaltests.pages;

import org.bloodtorrent.functionaltests.contexts.BloodTorrent;

import net.sf.sahi.client.Browser;
import net.sf.sahi.client.ElementStub;

public class HomePage extends BasePage {
	public HomePage(Browser browser) {
		super(browser);
	}

	public String getTitle() {
		return browser.span("wsite-title").text();
	}
	
	public void registerDonor() {
		browser.span("Register as donor").click();
	}
	
	public void requestForBlood() {
		browser.span("Request blood").click();
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
		return browser.byId("successStoryTitle");
	}

	public ElementStub getReadMoreLink() {
		return browser.link("successStoryDetailLink");
	}

	public void getDetailSuccessStory() {
		getReadMoreLink().click();		
	}

	public void gotoLoginPage() {
		browser.navigateTo(BloodTorrent.url + "successStory/list");		
	}
}
