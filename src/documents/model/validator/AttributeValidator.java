package documents.model.validator;

import java.util.HashMap;
import java.util.Map;

import documents.classes.MyLogger;
import documents.classes.ValidateRules;
import documents.classes.Validator;
import documents.model.data.Attribute;

public class AttributeValidator implements Validator {
	
	private Attribute[] attrs;
	private Map<String, String> values;
	private boolean valid = false;
	private Map<String, String> errors = new HashMap<String, String>();
	private ValidateRules rules = new ValidateRules();
	
	public AttributeValidator(Attribute[] attrs, Map<String, String> values) {
		this.attrs = attrs;
		this.values = values;
	}
	
	public void validate() {
		
		boolean firstDateIsSet = false;
		String firstDate = null;
		
		for(Attribute attr : attrs) {
			attr.setDefaultValue(values.get(Integer.toString(attr.getAttribute_type())));				
		}
		
		for(Attribute attr : attrs){
			if(attr.getData_type() != 4 && attr.isReq() && rules.isEmpty(attr.getDefaultValue())) {
				errors.put(idToKey(attr.getAttribute_type()), "See vali on kohustuslik");
				continue;
			} else if (!rules.isEmpty(attr.getDefaultValue())) {
				switch (attr.getData_type()){
					case 1:
						
						switch(attr.getAttribute_type()) {
							case 1:
								if(!rules.chechWithRegex(attr.getDefaultValue(), "([a-zA-Z ]*,?)*"))
									errors.put(idToKey(attr.getAttribute_type()), "Koosneb tahtedest ja tyhikutest, nimede eraldamiseks koma");
								break;
							case 3:
								if(!rules.isAlphaNumeric(attr.getDefaultValue()))
									errors.put(idToKey(attr.getAttribute_type()), "Tohib koosneda ainult tahtedes, numbrites, tyhikutest, _'st ja -'st");
								break;
							case 8:
								if(!rules.isValidMail(attr.getDefaultValue()))
									errors.put(idToKey(attr.getAttribute_type()), "Ebakorrektne e-maili address");
								break;		
						}
						
						break;
					case 2:
						if(rules.isPositiveNumeric(attr.getDefaultValue())) {
							
							switch(attr.getAttribute_type()) {
								case 7:
									if(!(rules.minSize(attr.getDefaultValue(), 5) && rules.maxSize(attr.getDefaultValue(), 12)))
										errors.put(idToKey(attr.getAttribute_type()), "Arve numbri pikkus peab olema 5 kuni 12 numbrit");
									break;
								case 9:
									if(Integer.parseInt(attr.getDefaultValue().trim()) < 2)
										errors.put(idToKey(attr.getAttribute_type()), "Osapoolte arv peab olema vahemalt 2");
									break;
							}
							
						} else {
							errors.put(idToKey(attr.getAttribute_type()), "Peab olema positiivne numberityypi vaartus");
						}
						
						break;
					case 3:
						if(rules.isValidaDate(attr.getDefaultValue())) {
							
							MyLogger.LogMessage("valid date " + attr.getAttribute_type());
							
							switch(attr.getAttribute_type()) {
							case 13:
							case 14:
								MyLogger.LogMessage("13 voi 14 " + attr.getAttribute_type());
								if(firstDateIsSet){
									if(attr.getAttribute_type() == 14) {
										if(rules.compareDates(attr.getDefaultValue(), firstDate) != -1) {
											errors.put(idToKey(attr.getAttribute_type()), "kehtivuse algus peab olema vaiksem kehtivuse lopust");
										} 
									} else {
										if(rules.compareDates(attr.getDefaultValue(), firstDate) != 1) {
											errors.put(idToKey(attr.getAttribute_type()), "kehtivuse algus peab olema vaiksem kehtivuse lopust");
										}
									}
								} else {
									firstDateIsSet = true;
									firstDate = attr.getDefaultValue();
								}
								break;
							}
							
						} else {
							errors.put(idToKey(attr.getAttribute_type()), "Korrektne kuupaeva formaat on dd.mm.yyyy");
						}
						break;
				}
			}
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
	
	private String idToKey(int id) {
		return "attr_" + id;
	}
	
	

}
