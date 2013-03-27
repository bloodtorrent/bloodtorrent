package org.bloodtorrent.functionaltests.pages;

import static junit.framework.Assert.assertTrue;
import net.sf.sahi.client.Browser;
import net.sf.sahi.client.BrowserCondition;
import net.sf.sahi.client.ExecutionException;

public class MapForSavingLocation extends BasePage {
	static int MAX_WAIT_MILISEC = 5000;
	private String locationPin = "Your Location";
		
	public MapForSavingLocation(Browser browser){
		super(browser);
	}
	
	public boolean isLocationPinVisible(){
		
	   BrowserCondition condition = new BrowserCondition(browser){
           public boolean test() throws ExecutionException {
                   return browser.area(locationPin).exists();
           }};
       browser.waitFor(condition, MAX_WAIT_MILISEC);
		
		return browser.area(locationPin).exists();
	}
	
	public String returnAlertText() {
		return  browser.div("dialog_confirm").text();
	}

	public void cancelSavingLocation() {
		browser.button("Cancel").near(browser.button("Save")).click();
	}

	public void saveLocation() {
		browser.button("Save").click();
	}
}
