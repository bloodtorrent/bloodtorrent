package org.bloodtorrent.functionaltests.scenarios;

import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;
import net.sf.sahi.client.Browser;

import org.bloodtorrent.functionaltests.contexts.BloodTorrent;
import org.bloodtorrent.functionaltests.pages.Admin;

public class OnAdminPage {

	private Admin admin;

	private static String SUCCESS_STORY_TITLE = "How we helped";
	
	public OnAdminPage(Browser browser) {
		admin = new Admin(browser);
	}

	public void verifyAdminPage() throws Exception {
		String url = BloodTorrent.url;
		verifyEquals(  url + "successStory/list", admin.getUrl());
	}

	public void logout() throws Exception {
		admin.signOff();
	}

	public void goToCreateSuccessStoriesPage() throws Exception {
		admin.goToCreateSuccessStroy();
	}

	public void verifySuccessStoryListPage() throws Exception {
		verifyEquals(SUCCESS_STORY_TITLE, admin.getTitleofSuccessStoryList());
	}

	public void verifyMessageSuccessStoryIsCreatedSuccessfully() throws Exception {
	
	}

}