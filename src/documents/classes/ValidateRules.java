package documents.classes;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidateRules {
	
	public boolean minSize(String str, int minSize){
		if(str.trim().length() <= minSize)
			return false;
		return true;
	}
	
	public boolean maxSize(String str, int maxSize){
		if(str.trim().length() >= maxSize)
			return false;
		return true;
	}
	
	public boolean isEmpty(String str){
		if(str == null || str.trim().equals(""))
			return true;
		return false;
	}
	
	public boolean isPositiveNumeric(String str) {
		if(isNumeric(str) && Integer.parseInt(str.trim()) > 0){
			return true;
		}
		return false;
	}
	
	public boolean isNumeric(String str){
		try {
			Integer.parseInt(str.trim());
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean isAlphaBetic(String str) {
		if(str.trim().matches("[a-zA-Z _-]*"))
			return true;
		return false;
	}
	
	public boolean isAlphaNumeric(String str) {
		if(str.trim().matches("[a-zA-Z0-9 _-]*"))
			return true;
		return false;
	}
	public boolean isValidaDate(String str) {
		if(str.trim().matches("^(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.((19|20)\\d\\d)$"))
			return true;
		return false;
	}
	
	public boolean isValidMail(String str) {
		if(str.trim().matches("^.+@.+\\.[a-z]+$"))
			return true;
		return false;
	}
	
	public boolean chechWithRegex(String str, String regex) {
		if(str.trim().matches(regex))
			return true;
		return false;
	}
	
	public int compareDates (String date1, String date2) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		int result = 9999;
		MyLogger.LogMessage("start comparing");
		try {
			Date parsedDate1 = dateFormat.parse(date2.trim());
			Date parsedDate2 = dateFormat.parse(date1.trim());
			long timestamp1 = new Timestamp(parsedDate1.getTime()).getTime();
			long timestamp2 = new Timestamp(parsedDate2.getTime()).getTime();
			
			if(timestamp1 > timestamp2) {
				result = 1;
			} else if(timestamp1 < timestamp2) {
				result = -1;
			} else {
				result = 0;
			}
			
			MyLogger.LogMessage("compare " + timestamp1 + " " + timestamp2 + " " + result);
			
		} catch (ParseException e) {
			MyLogger.Log("GeneralFunction.compareDates", e.toString());
		}
		return result;
	}

}
