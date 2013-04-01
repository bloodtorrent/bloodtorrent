package org.bloodtorrent.functionaltests.pages;

import net.sf.sahi.client.Browser;
import net.sf.sahi.client.BrowserCondition;
import net.sf.sahi.client.ExecutionException;

public class MapPopup extends BasePage {
	static int MAX_WAIT_MILISEC = 5000;
	private String locationPin = "Your Location";
		
	public MapPopup(Browser browser){
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

	public void specifyNewLocation(String latitude, String longitude) {
		String combinedLongitudeLatitude = latitude + "," + longitude;
		System.out.println(combinedLongitudeLatitude);
		browser.textbox("search_address").setValue(combinedLongitudeLatitude);
		browser.textbox("searchButton").click();	
	}
}
