package documents.classes;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class GeneralFunction {
	
	public int parseStringToInt(String value, int defaultValue) {
		int result = defaultValue;
		try {
			result = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			
		}
		return result;
	}
	
	public String stringIsSet(String value, String defaultValue){
		return (value == null ? defaultValue : value);
	}
	
	public boolean emptyString(String string) {
		if(string == null || string.trim().length() == 0)
			return true;
		return false;
	}
	
	public Map<String, String> getAttributesValues(HttpServletRequest request) {
		Map<String, String> attrs = new HashMap<String, String>();
				
		Enumeration<String> names = request.getParameterNames();
	
		String name;
		
		while(names.hasMoreElements()){
			name = names.nextElement();
			if(name.contains("attr_")){
				attrs.put(name.replaceAll("attr_", ""), request.getParameter(name).toString());
			}

		}
		
		return attrs;
	}
	
	public String dateToSQLFormat(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
		String result = "";
		try {
			Date parsedDate = dateFormat.parse(date);
			Date dateObject = new Date(parsedDate.getTime());
			result = output.format(dateObject);
		} catch (ParseException e) {
			MyLogger.Log("GeneralFunctions.dateToSQLFormat()", e.toString());	
		}
		return result;
	}
	
	public String sqlDateToString(String date) {
		SimpleDateFormat output = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String result = "";
		try {
			Date parsedDate = dateFormat.parse(date);
			Date dateObject = new Date(parsedDate.getTime());
			result = output.format(dateObject);
		} catch (ParseException e) {
			MyLogger.Log("GeneralFunctions.sqlDateToString()", e.toString());	
		}
		return result;
	}
	
}
