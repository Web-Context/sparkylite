/**
 * file: MongoRepositoryDb.java
 * date: 14 janv. 2016
 *
 * GEHC DoseWatch
 *
 * Copyright (c) 2016 by General Electric Company. All rights reserved.
 * 
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 *
 */

package com.webcontext.apps.utils.mongo;

import java.util.Arrays;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.webcontext.apps.utils.config.Configuration;

/**
 * Database definition for a Mongo repository.
 * 
 * @author Frédéric Delorme
 *
 */
public class MongoRepositoryDb {
	/**
	 * MOngo client to access database
	 */
	public MongoClient client;
	/**
	 * targeted Mongo Database
	 */
	private MongoDatabase database;

	public MongoRepositoryDb() {
		this.connection();
	}

	/**
	 * @return
	 */
	public MongoDatabase getDatabase() {
		if (database == null) {
			database = client.getDatabase(Configuration.get("mongo.database", "sparkygames"));
		}
		return database;
	}

	/**
	 * Connection to Database
	 */
	public MongoClient connection() {
		// initialize mongoDb connection to just started server.
		ServerAddress mongoServerAddr = new ServerAddress(Configuration.get("mongo.hostname", "localhost"),
				Integer.parseInt(Configuration.get("mongo.port", "27017")));
		try {

			if (Configuration.get("mongo.securize", "false").equals("true")) {
				MongoCredential credential = MongoCredential.createCredential(
						Configuration.get("mongo.username", "sparky"),
						Configuration.get("mongo.database", "sparkygames"),
						Configuration.get("mongo.password", "ykraps").toCharArray());

				client = new MongoClient(Arrays.asList(mongoServerAddr), Arrays.asList(credential));
			} else {
				client = new MongoClient(Arrays.asList(mongoServerAddr));
			}
		} catch (Exception e) {
			System.err.println("Unabke to connect to database " + mongoServerAddr);
		}
		return client;
	}

}
