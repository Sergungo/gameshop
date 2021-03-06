package com.golenko.gameshop.dao;

//import java.io.FileInputStream;
//import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;

public class MySqlConnection {
	private static Connection CONNECTION;
	private static String URL = "jdbc:mysql://localhost:3306/gameshop?useLegacyDatetimeCode=false&serverTimezone=UTC";
//	private static String USERNAME = "root";
//	private static String PASSWORD = "";
	private static String USERNAME = "root";
	private static String PASSWORD = "usbw";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            System.out.println("Connection succesfull!");;

		} catch (Exception e) {
			 System.out.println("Connection failed!");; 	
			System.out.println(e.getMessage());
			
		}

//		Properties prop = new Properties();
//		FileInputStream in = null;
//		try {
//			in = new FileInputStream("database.properties");
//
//			prop.load(in);
//			in.close();
//		} catch (IOException e) {
//			System.out.println("Couldn't download " + "database properties.");
//		}
//		URL = prop.getProperty("jdbc.url");
//		USERNAME = prop.getProperty("jdbc.username");
//		PASSWORD = prop.getProperty("jdbc.password");
	}

	private MySqlConnection() {

	}

	public static Connection getConnection() {
		try {
			if (CONNECTION == null || CONNECTION.isClosed()) {
				CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CONNECTION;
	}
}
