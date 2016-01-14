/**
 * file: MongoJsonImport.java
 * date: 13 janv. 2016
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

import java.io.IOException;
import java.net.UnknownHostException;

import com.mongodb.MongoClient;
import com.webcontext.apps.utils.config.Configuration;

import de.flapdoodle.embed.mongo.MongoImportExecutable;
import de.flapdoodle.embed.mongo.MongoImportProcess;
import de.flapdoodle.embed.mongo.MongoImportStarter;
import de.flapdoodle.embed.mongo.config.IMongoImportConfig;
import de.flapdoodle.embed.mongo.config.MongoImportConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

/**
 * Import JSON data into database.
 * 
 * 
 * @author Frederic Delorme
 *
 */
public class MongoJsonImport {

	/**
	 * Import <code>jsonFile</code> data into <code>collection</code> of defined database.
	 * 
	 * @param jsonFile
	 * @param collection
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void importJson( String jsonFile, String collection ) throws UnknownHostException, IOException {
		int port = Integer.parseInt(Configuration.get("mongo.port", "21017"));
		String host = Configuration.get("mongo.database", "localhost");
		MongoImportProcess mongoImport = null;
		try {
	
			mongoImport = mongoImportJSONData(port, host, collection, jsonFile, true, true, true);
			
			MongoClient mongoClient = new MongoClient(host, port);
			
			System.out.println("DB Names: " + mongoClient.listDatabaseNames());
		
		} finally {
			if (mongoImport != null) {
				mongoImport.stop();
			}
		}
	}

	/**
	 * Import JSON file data to MongoDb collection into already connected database.
	 * 
	 * @param port
	 * @param dbName
	 * @param collection
	 * @param jsonFile
	 * @param jsonArray
	 * @param upsert
	 * @param drop
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private MongoImportProcess mongoImportJSONData( int port, String dbName, String collection, String jsonFile,
			Boolean jsonArray, Boolean upsert, Boolean drop ) throws UnknownHostException, IOException {
		IMongoImportConfig mongoImportConfig = new MongoImportConfigBuilder().version(Version.Main.PRODUCTION)
				.net(new Net(port, Network.localhostIsIPv6())).db(dbName).collection(collection).upsert(upsert)
				.dropCollection(drop).jsonArray(jsonArray).importFile(jsonFile).build();

		MongoImportExecutable mongoImportExecutable = MongoImportStarter.getDefaultInstance()
				.prepare(mongoImportConfig);
		MongoImportProcess mongoImport = mongoImportExecutable.start();
		return mongoImport;
	}
}
