package documents.classes;

import org.apache.log4j.*;

public class MyLogger  {

	static Logger log = Logger.getLogger(MyLogger.class);

	public static void Log(String method_name,String msg) {
		
		String log_row="APP_ERROR:" + " method=" + method_name + " error_message=" + msg;
		try {
			log.info(log_row);
		} catch(Exception ex) { 
			System.out.println("MyLogger.Log():" + ex.getMessage());
		}
	}

	public static void LogMessage(String msg) {
		
		String log_row="APP_MESSAGE:"  + msg;

		try { 
			log.info(log_row);
		} catch(Exception ex) { 
			System.out.println("MyLogger.Log():" + ex.getMessage());
		}
	}
}
