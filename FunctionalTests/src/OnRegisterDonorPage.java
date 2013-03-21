// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnRegisterDonorPage {

	private Browser browser;

	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public OnRegisterDonorPage(Browser browser) {
		this.browser = browser;
	}

	public void fillOutTheValidInformationToMandatoryFields() throws Exception {
		browser.textbox("firstName").setValue("Jane");
		browser.textbox("lastName").setValue("Jung");
		browser.textbox("email").setValue("Jane@samsung.com");
		browser.password("password").setValue("12341234");
		browser.password("confirmPassword").setValue("12341234");
		browser.textarea("address").setValue("Seoul");
		browser.textbox("city").setValue("Seoul");
		browser.select("state").choose("Orissa");
		browser.textbox("cellPhone").setValue("0102003000");
		browser.select("bloodGroup").choose("B+");
		browser.select("distance").choose("10");
	}

	public void registerDonor() throws Exception {
		browser.submit("Register").click();
	}


}