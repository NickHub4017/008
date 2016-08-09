package Handlers;

import java.sql.SQLException;
import java.util.Date;

import Bussiness.User;
import Database.DBLink;

public class MessageHandler {

	public static void putMessage(String msg, User user) throws SQLException {
		DBLink.putMessage(msg,user.getId(),new Date());
		
	}

}
