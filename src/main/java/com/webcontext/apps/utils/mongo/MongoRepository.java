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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.UnknownHostException;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mongodb.client.FindIterable;
import com.mongodb.util.JSON;

/**
 * @author Frederic
 *
 */
public class MongoRepository<T> extends MongoRepositoryDb {

	/**
	 * Collection linked to this Repo.
	 */
	private String collectionName;

	private Class<T> inferedClass;

	@SuppressWarnings("unchecked")
	protected Class<T> getGenericClass() {
		if (inferedClass == null) {
			Type mySuperclass = getClass().getGenericSuperclass();
			Type tType = ((ParameterizedType) mySuperclass).getActualTypeArguments()[0];
			String className = tType.toString().split(" ")[1];
			try {
				inferedClass = (Class<T>) Class.forName(className);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return inferedClass;
	}

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

	/**
	 * Basic search into specific collection for a type class.
	 * 
	 * @param filter
	 *            JSON filtering for type class.
	 * @param offset
	 *            first element to retrieve
	 * @param limit
	 *            number of element to be retrieved
	 * @return
	 */
	public FindIterable<Object> find( Bson filter, int offset, int limit ) {
		return find(collectionName, getGenericClass(), filter, offset, limit);
	}

	/**
	 * Retrieve all data for type class.
	 * 
	 * @param type
	 * @return
	 */
	protected FindIterable<Object> findAll() {
		return find(collectionName, getGenericClass(), null, 0, 0);
	}

	/**
	 * Import <code>jsonFile</code> data into <code>collection</code> of defined database.
	 * 
	 * @param jsonFile
	 * @param collection
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void importJson( String jsonFile ) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(jsonFile));
			JsonArray jsonArray = new JsonParser().parse(br).getAsJsonArray();
			for (int i = 0; i < jsonArray.size(); i++) {
				JsonElement str = jsonArray.get(i);
				Document jsonObject = (Document) JSON.parse(str.getAsString());
				// T obj = gson.fromJson(str, getGenericClass());
				getDatabase().getCollection(collectionName).insertOne(jsonObject);
			}
		} catch (IOException e) {
			System.err.println("Unable to find data from " + jsonFile);
		}

	}

}
