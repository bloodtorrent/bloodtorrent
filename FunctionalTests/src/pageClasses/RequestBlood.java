package pageClasses;

import net.sf.sahi.client.Browser;

public class RequestBlood {

	private Browser browser;

	public RequestBlood(Browser browser){
		this.browser = browser;
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
}
