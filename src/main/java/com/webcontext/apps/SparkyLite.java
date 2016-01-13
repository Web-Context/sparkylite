package com.webcontext.apps;

import com.webcontext.apps.resources.PostResource;

/**
 * A small demonstration application to serve REST Post CRUD actions.
 * 
 * @author Frédéric Delorme
 *
 */
public class SparkyLite {

	/**
	 * Service for Post entities.
	 */
	PostResource postService;

	public static void main(String[] args) {
		/**
		 * Initialize application.
		 */
		SparkyLite sparky = new SparkyLite();
		/**
		 * Declare Resources for the PostService.
		 */
		sparky.declareResources();
	}

	public void declareResources() {
		postService = new PostResource();
		postService.init();
	}

}