package com.andrewpanasyuk.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {
	private static ConnectionFactory instance = new ConnectionFactory();
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String PASS = "root";
	private static final String USER = "postgres";
	private static final String DRIVER = "org.postgresql.Driver";
	@Resource(name="jdbc/UniverDB")
	private DataSource source;
	
	private ConnectionFactory(){
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Connection createConnection() throws NamingException{
		Context context = new InitialContext();
		Connection connection = null;
		try {
			connection = source.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	

	public static Connection getConnect() {
		try {
			return instance.createConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
