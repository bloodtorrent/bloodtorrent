package org.bloodtorrent.functionaltests.scenarios;

import net.sf.sahi.client.Browser;
import org.bloodtorrent.functionaltests.pages.MapForSavingLocation;
import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;
import static junit.framework.Assert.assertTrue;

public class OnMapPage {

	private MapForSavingLocation map;

	public OnMapPage(Browser browser) {
		map = new MapForSavingLocation(browser);  
	}

	public void verifyDisplayedMap() throws Exception {
		assertTrue(map.isLocationPinVisible());
	}
	
	public void cancelMap() throws Exception {
		map.cancelSavingLocation();
	}

	public void verifyMessageForCancel() throws Exception {
		verifyEquals(map.returnAlertText(), "Do you wish to proceed without specifying exact location?");
	}

	public void saveLocation() throws Exception {
		map.saveLocation();
	}
}