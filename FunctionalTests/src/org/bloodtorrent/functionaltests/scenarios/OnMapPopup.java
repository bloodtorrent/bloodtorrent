package org.bloodtorrent.functionaltests.scenarios;

import net.sf.sahi.client.Browser;
import org.bloodtorrent.functionaltests.pages.MapPopup;
import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;
import static junit.framework.Assert.assertTrue;

public class OnMapPopup {

	private MapPopup mapPopup;

	public OnMapPopup(Browser browser) {
		mapPopup = new MapPopup(browser);  
	}

	public void verifyMapIsVisible() throws Exception {
		assertTrue(mapPopup.isLocationPinVisible());
	}
	
	public void cancelMap() throws Exception {
		mapPopup.cancelSavingLocation();
	}

	public void verifyMessageForCancel() throws Exception {
		verifyEquals(mapPopup.returnAlertText(), "Do you wish to proceed without specifying exact location?");
	}

	public void saveLocation() throws Exception {
		mapPopup.saveLocation();
	}

	public void specifyNewLocation() throws Exception {
		mapPopup.specifyNewLocation("18.5", "73.8");
	}
}