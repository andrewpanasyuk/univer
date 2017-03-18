package com.andrewpanasyuk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static ConnectionFactory instance = new ConnectionFactory();
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String PASS = "root";
	private static final String USER = "postgres";
	private static final String DRIVER = "org.postgresql.Driver";
	
	private ConnectionFactory(){
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Connection createConnection(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	

	public static Connection getConnect() {
		return instance.createConnection();
	}

}
