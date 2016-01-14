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

package com.webcontext.apps.sparky.tests.utils;

import org.junit.After;
import org.junit.Before;

import com.webcontext.apps.utils.mongo.EmbeddedMongoDbServer;

/**
 * Set and instaciate a Mongo Database server for test purpose.
 * 
 * @author Frederic Delorme
 *
 */
public class EmbeddedMongoTest {

	EmbeddedMongoDbServer mongoServer = new EmbeddedMongoDbServer();

	@Before
	public void before() {
		mongoServer.start();
	}

	@After
	public void after() {
		mongoServer.stop();
	}

}
