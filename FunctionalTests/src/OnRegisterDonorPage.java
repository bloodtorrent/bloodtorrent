// JUnit Assert framework can be used for verification

import java.util.Random;

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

	private void setMendatoryFieldsWithoutEmail() {
		browser.textbox("firstName").setValue("Jane");
		browser.textbox("lastName").setValue("Jung");
		browser.password("password").setValue("12341234");
		browser.password("confirmPassword").setValue("12341234");
		browser.textarea("address").setValue("Seoul");
		browser.textbox("city").setValue("Seoul");
		browser.select("state").choose("Orissa");
		browser.textbox("cellPhone").setValue("0102003000");
		browser.select("bloodGroup").choose("B+");
		browser.select("distance").choose("10");
	}
	
	public void fillOutTheValidInformationToMandatoryFields() throws Exception {
		
		Random rd= new Random();
		
		int rdEmailcount = rd.nextInt(1000)*2;
		
		String rdEmail = rdEmailcount + "id@samsung.com";
		browser.textbox("email").setValue(rdEmail);
		
		setMendatoryFieldsWithoutEmail();
	}


	public void registerDonor() throws Exception {
		browser.submit("Register").click();
	}

	public void cancelRegistration() throws Exception {
		browser.button("cancel").click();			
	}

	public void fillOutTheValidInformationToMendatoryFieldsWithExistingUsersEmailAddress(
			String email) throws Exception {
		browser.textbox("email").setValue(email);
		setMendatoryFieldsWithoutEmail();
	}

	public void fillOutTheAddressInformation() throws Exception {
		browser.textarea("address").setValue("Pune");
		browser.textbox("city").setValue("Pune");
		browser.select("state").choose("Maharashtra");
	
	}

	public void openMapsToSelectAccurateLocation() throws Exception {
		browser.button("map").click();
	
	}


}