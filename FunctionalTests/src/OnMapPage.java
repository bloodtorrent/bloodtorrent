import net.sf.sahi.client.Browser;
import net.sf.sahi.client.BrowserCondition;
import net.sf.sahi.client.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

import static junit.framework.Assert.assertTrue;

public class OnMapPage {

	private Browser browser;

	@Autowired
	private TwistScenarioDataStore scenarioStore;
	private String locationPin = "Your Location";

	public OnMapPage(Browser browser) {
		this.browser = browser;
	}

	public void verifyDisplayedMap() throws Exception {
		assertTrue(isLocationPinVisible());
	}
	
	public boolean isLocationPinVisible(){
		return browser.area(locationPin).exists();
	}
}