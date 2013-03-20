// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnRequestBloodPage {

	private Browser browser;

	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public OnRequestBloodPage(Browser browser) {
		this.browser = browser;
	}

	public void fillOutTheValidRequestInformationToMendatoryFields()
			throws Exception {
		browser.textbox("firstName").setValue("jaemin");
		browser.textbox("lastName").setValue("Kim");
		browser.textarea("hospitalAddress").setValue("b hos");
		browser.textbox("city").setValue("pune");
		browser.select("state").choose("Tripura");
		browser.textbox("phone").setValue("1234567890");
		browser.textbox("email").setValue("kmgunjun@naver.com");
		browser.textbox("birthday").setValue("15-11-1979");
		browser.textbox("bloodVolume").setValue("33");
		browser.radio("requesterType[1]").click();
	}
	
	public void requestBlood() throws Exception {
		browser.submit("Register").click();
	}

	public void cancelRequest () throws Exception {
		browser.button("Cancel").click();
	
	}

}