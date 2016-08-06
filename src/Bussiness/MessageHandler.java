package Bussiness;

import java.sql.SQLException;
import java.util.Date;

public class MessageHandler {

	public static void putMessage(String msg, User user) throws SQLException {
		DBLink.putMessage(msg,user.getId(),new Date());
		
	}

}
