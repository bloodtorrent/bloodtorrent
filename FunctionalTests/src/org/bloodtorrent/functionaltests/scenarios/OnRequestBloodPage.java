package org.bloodtorrent.functionaltests.scenarios;

import net.sf.sahi.client.Browser;
import org.bloodtorrent.functionaltests.pages.RequestBlood;
import static junit.framework.Assert.*;

public class OnRequestBloodPage {

	String patientFirstName = "jaemin";
	String patientLastName = "kim";
	String hospitalAddress ="B hospital";
	String patientCity = "pune"; 
	String patientState = "Tripura";
	String patientPhoneNumber = "1234567890";
	String patientEmailAddress = "kmgunjun@naver.com";
	String patientBirthday = "15-11-1980";
	String bloodVolume = "33";
	
	private RequestBlood requestBlood;

	public OnRequestBloodPage(Browser browser) {
		requestBlood = new RequestBlood(browser);
	}
	
	public void fillOutTheValidRequestInformationToMendatoryFields()
			throws Exception {
		
		requestBlood.setFirstName(patientFirstName);
		requestBlood.setLastName(patientLastName);
		requestBlood.setHospitaAddress(hospitalAddress);
		requestBlood.setPatientCity(patientCity);
		requestBlood.setPatientState(patientState);
		requestBlood.setPhone(patientPhoneNumber);
		requestBlood.setEmail(patientEmailAddress);
		requestBlood.setBirthday(patientBirthday);
		requestBlood.setBloodVolume(bloodVolume);
		requestBlood.setRequesterTypeToPatient();
	}
	
	public void requestBlood() throws Exception {
		requestBlood.register();
	}

	public void cancelRequest () throws Exception {
		requestBlood.cancel();
	}

	public void verifyErrorMessage(String errorMessage) throws Exception {		
		assertEquals(errorMessage, requestBlood.getErrorMessage());	
	}

	public void fillOutInvalidPhoneNumber() throws Exception {
		
		fillOutTheValidRequestInformationToMendatoryFields();
		requestBlood.setPhone("11112222ss");	
	}

	public void fillOutInvalidEmailAddress() throws Exception {
		fillOutTheValidRequestInformationToMendatoryFields();
		requestBlood.setEmail("11112222ss");		
	}

}