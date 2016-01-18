package com.webcontext.apps.utils.service;

import javax.annotation.PostConstruct;

import com.webcontext.apps.entities.Post;
import com.webcontext.apps.utils.mongo.MongoRepository;

/**
 * BootStrap Application
 * 
 * @author Frederic
 *
 */
public class BootStrap {

	MongoRepository<Post> posts = new MongoRepository<Post>("games");

	public BootStrap() {
		if (!(posts.count() > 0)) {

			posts.importJson(this.getClass().getResource("/").getPath() + "dataset/games.json");

		}
	}

	@PostConstruct
	public void start() {

	}

}
