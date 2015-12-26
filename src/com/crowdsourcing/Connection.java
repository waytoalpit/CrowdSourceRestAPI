package com.crowdsourcing;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.logging.Logger;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class Connection {

	Logger log = Logger.getLogger("Connection");
	Properties prop = null;

	MongoClient mongoClient = null;
	DB db = null;

	public Connection() {

		prop = new Properties();
		try {
			prop.load(getClass().getResourceAsStream("/com/resources/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean authenticateDB() {

		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		boolean auth = db.authenticate(username, password.toCharArray());
		log.info("Authentication done: " + auth);

		return auth;
	}

	public DB connectMongoDB() {

		try {

			String dburl = prop.getProperty("dburl");
			String port = prop.getProperty("port");

			mongoClient = new MongoClient(dburl, Integer.parseInt(port));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		String selectedDB = prop.getProperty("database");
		db = mongoClient.getDB(selectedDB);
		log.info("Connect to SpecsenseDB database successfully");
		return db;

	}
}
