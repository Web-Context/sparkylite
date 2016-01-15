/**
 * file: MongoRepository.java
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

import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;

/**
 * @author Frederic
 *
 */
public class MongoRepository extends MongoRepositoryDb {

	/**
	 * Collection linked to this Repo.
	 */
	private String collectionName;

	public MongoRepository() {
		super();
		this.collectionName = "repodefault";
	}

	/**
	 * Constructor over a specific Collection.
	 * 
	 * @param collectionName
	 */
	public MongoRepository(String collectionName) {
		this();
		this.collectionName = collectionName;
	}

	/**
	 * Count number of items in <code>collectionName</code> collection.
	 * 
	 * @param collectionName
	 * @return
	 */
	protected long count( String collectionName ) {
		return getDatabase().getCollection(collectionName).count();
	}

	/**
	 * Retrieve MongoDb document mapped to <code>type</code> object, filtered with <code>filter</code> bson object and starting at
	 * <code>offset</code> for <code>limit</code> number of results
	 * 
	 * @param collectionName
	 * @param type
	 * @param filter
	 * @param offset
	 * @param limit
	 * @return
	 */
	protected FindIterable<Object> find( String collectionName, Class type, Bson filter, int offset, int limit ) {
		return getDatabase().getCollection(collectionName).find(filter, type).skip(offset).limit(limit);
	}

	/**
	 * Retrieve all <code>type</code> object from collectionName.
	 * 
	 * @param collectionName
	 * @param type
	 * @return
	 */
	protected FindIterable<Object> findAll( String collectionName, Class type ) {
		return find(collectionName, type, null, 0, 0);
	}

	/*-------- PUBLIC METHODS --------*/

	/*
	 * @see MongoRepository#count(String)
	 */
	public long count() {
		return count(collectionName);
	}

	public FindIterable<Object> find( Bson filter, int offset, int limit, Class type ) {
		return find(collectionName, type, filter, offset, limit);
	}

	protected FindIterable<Object> findAll( Class type ) {
		return find(collectionName, type, null, 0, 0);
	}

}
