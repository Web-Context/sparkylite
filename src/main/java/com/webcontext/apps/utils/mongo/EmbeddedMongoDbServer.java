/**
 * file: EmbeddedMongoTest.java
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

import com.webcontext.apps.utils.config.Configuration;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class EmbeddedMongoDbServer {

	MongodStarter starter = null;
	MongodExecutable mongodExecutable = null;

	/**
	 * default Constructor for embedded server.
	 */
	public EmbeddedMongoDbServer() {
		starter = MongodStarter.getDefaultInstance();
	}

	/**
	 * Start Database.
	 */
	public void start() {
		int port = Integer.parseInt(Configuration.get("mongo.port", "27017"));
		String hostname = Configuration.get("mongo.hostname", "localhost");
		IMongodConfig mongodConfig;
		try {
			mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
					.net(new Net(port, Network.localhostIsIPv6())).build();

			// Initialize and Start mongo db server.
			mongodExecutable = starter.prepare(mongodConfig);
			MongodProcess mongod = mongodExecutable.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stop();
		}
	}

	/**
	 * Stop MongoDB database server if running.
	 */
	public void stop() {
		if (mongodExecutable != null) {
			mongodExecutable.stop();
		}
	}

}
