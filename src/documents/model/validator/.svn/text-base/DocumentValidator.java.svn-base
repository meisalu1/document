package documents.model.validator;

import java.util.HashMap;
import java.util.Map;

import documents.classes.MyLogger;
import documents.classes.ValidateRules;
import documents.classes.Validator;
import documents.model.data.Document;

public class DocumentValidator implements Validator {

	private Document doc;
	private boolean valid = false;
	private Map<String, String> errors = new HashMap<String, String>();
	private ValidateRules rules = new ValidateRules();
	
	public DocumentValidator(Document doc) {
		this.doc = doc;
	}
	
	@Override
	public void validate() {
		if(rules.isEmpty(doc.getName())) {
			errors.put("name", "Nimi on kohustuslik vali");
		} else if (!rules.isAlphaNumeric(doc.getName())) {
			MyLogger.LogMessage("sobimatu name");
			errors.put("name", "Nimi peab koosnema numbrites, tahtedest ja tyhikutest");
		}		
		
		if(errors.isEmpty())
			valid = true;
		
	}

	public boolean isValid() {
		return valid;
	}

	public Map<String, String> getErrors() {
		return errors;
	}
	
	

}
