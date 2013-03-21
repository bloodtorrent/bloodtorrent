import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;


public class OnRequestBloodPage {

	private Browser browser;
	
	String patientFirstName = "jaemin";
	String patientLastName = "kim";
	String hospitalAddress ="B hospital";
	String patientCity = "pune"; 
	String patientState = "Tripura";
	String patientPhoneNumber = "1234567890";
	String patientEmailAddress = "kmgunjun@naver.com";
	String patientBirthday = "15-11-1980";
	String bloodVolume = "33";
	
	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public OnRequestBloodPage(Browser browser) {
		this.browser = browser;
	}
	
	public void fillOutTheValidRequestInformationToMendatoryFields()
			throws Exception {
		
		browser.textbox("firstName").setValue(patientFirstName);
		browser.textbox("lastName").setValue(patientLastName);
		browser.textarea("hospitalAddress").setValue(hospitalAddress);
		browser.textbox("city").setValue(patientCity);
		browser.select("state").choose(patientState);
		browser.textbox("phone").setValue(patientPhoneNumber);
		browser.textbox("email").setValue(patientEmailAddress);
		browser.textbox("birthday").setValue(patientBirthday);
		browser.textbox("bloodVolume").setValue(bloodVolume);
		browser.radio("requesterType[1]").click();
	}
	
	public void requestBlood() throws Exception {
		browser.submit("Register").click();
	}

	public void cancelRequest () throws Exception {
		browser.button("Cancel").click();
	
	}

}