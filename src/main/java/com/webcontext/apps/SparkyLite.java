package com.webcontext.apps;

import static spark.Spark.get;

import javax.annotation.PreDestroy;

import com.webcontext.apps.resources.PostResource;
import com.webcontext.apps.resources.Resource;
import com.webcontext.apps.utils.config.Configuration;
import com.webcontext.apps.utils.json.JsonTransformer;
import com.webcontext.apps.utils.mongo.EmbeddedMongoDbServer;
import com.webcontext.apps.utils.service.BootStrap;

/**
 * A small demonstration application to serve REST Post CRUD actions.
 * 
 * @author Frédéric Delorme
 *
 */
public class SparkyLite {

	EmbeddedMongoDbServer mongoDb = null;
	Boolean embeddedServer = Boolean.parseBoolean(Configuration.get("mongo.embedded", "false"));

	public SparkyLite() {
		if (embeddedServer) {
			EmbeddedMongoDbServer mongoDb = new EmbeddedMongoDbServer();
			mongoDb.start();
		}
		System.out.println("Server started");
	}

	public void bootstrap() {
		new BootStrap();
	}

	@PreDestroy
	public void destroy() {
		if (Boolean.parseBoolean(Configuration.get("mongo.embedded", "false")) && mongoDb != null) {
			mongoDb.stop();
		}
		System.out.println("Server Stop.");
	}

	private void declareResources() {
		postService = new PostResource();
		postService.init();
	}

	/**
	 * Service for Post entities.
	 */
	PostResource postService;

	public static void main( String[] args ) {
		/**
		 * Initialize application.
		 */
		SparkyLite sparky = new SparkyLite();
		/**
		 * Initialize things needed to be initialized.
		 */
		sparky.bootstrap();
		/**
		 * Declare Resources for the PostService.
		 */
		sparky.declareResources();
		get("/stop", Resource.ContentType.APPLICATION_JSON, ( request, response ) -> {
			System.exit(0);
			return "STOP";
		} , new JsonTransformer());
	}
}