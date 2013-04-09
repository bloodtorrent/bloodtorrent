package org.bloodtorrent.functionaltests.pages;

import net.sf.sahi.client.Browser;
import net.sf.sahi.client.BrowserCondition;
import net.sf.sahi.client.ExecutionException;

public class RequestBlood extends BasePage {

	private static final int DEFAULT_WAIT_MILLISECONDS = 2 * 1000;
	
	public RequestBlood(Browser browser){
		super(browser);
	}

	public void setFirstName(String patientFirstName) {
		browser.textbox("firstName").setValue(patientFirstName);		
	}

	public void setLastName(String patientLastName) {
		browser.textbox("lastName").setValue(patientLastName);		
	}

	public void setHospitaAddress(String hospitalAddress) {
		browser.textarea("hospitalAddress").setValue(hospitalAddress);
	}

	public void setPatientCity(String patientCity) {
		browser.textbox("city").setValue(patientCity);
	}

	public void setPatientState(String patientState) {
		browser.select("state").choose(patientState);
	}
	
	public void setPhone(String phone) {
		browser.textbox("phone").setValue(phone);
	}
	
	public void setEmail(String email) {
		browser.textbox("email").setValue(email);
	}
	
	public void setBirthday(String birthday) {
		browser.textbox("birthday").setValue(birthday);
	}
	
	public void setBloodVolume(String bloodVolume) {
		browser.textbox("bloodVolume").setValue(bloodVolume);
	}

	public void setRequesterTypeToPatient() {		
		browser.radio("requesterType[1]").click();
	}	
	
	public void register(){
		browser.byId("register").click();
	}

	public void cancel() {
		browser.byId("cancel").click();
	}

	public String getErrorMessage() {
		BrowserCondition condition = new BrowserCondition(browser) {
			
			String errorMessage = "";
			String changedMessage = "";
			
			public boolean test() throws ExecutionException {
				// initial : hide -> show up
				// change error : compare the messages
				errorMessage = browser.div("message error").getText();
				if (errorMessage == null) {
					return true;
				} else if(!changedMessage.equals(errorMessage)){
					
					changedMessage = errorMessage;
					return false;
				}else{
					return true;
				}
			}
		};
		browser.waitFor(condition, DEFAULT_WAIT_MILLISECONDS);
		return browser.div("message error").getText(); 
	}
	
}
