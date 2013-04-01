package org.bloodtorrent.functionaltests.pages;

import net.sf.sahi.client.Browser;

public class CreateSuccessStory extends BasePage {

	public CreateSuccessStory(Browser browser) {
		super(browser);
	}

	public void cancel() {
		browser.button("Cancel").click();
	}

	public void setTitle(String title) {
		browser.textbox("title").setValue(title);
	}

	public void setSummary(String summary) {
		browser.textbox("summary").setValue(summary);
	}
	
	public void setDescription(String description) {
		browser.textarea("description").setValue(description);
	}

	public void setImageFile(String imgPath) {
		browser.file("visualResourcePath").setFile(imgPath);
	}

	public void save() {
		browser.button("Save").click();
	}
}
