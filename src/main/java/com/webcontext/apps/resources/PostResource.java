package com.webcontext.apps.resources;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import com.google.gson.Gson;
import com.webcontext.apps.entities.Post;
import com.webcontext.apps.services.PostService;
import com.webcontext.apps.utils.JsonTransformer;

/**
 * Rest Service to manage Post CRUD operations.
 * 
 * @author Frédéric Delorme
 *
 */
public class PostResource extends Resource {

	PostService postService = new PostService();

	/**
	 * Define version for this API.
	 */
	public PostResource() {
		super("v1");
	}

	public void init() {
		// retrieve some data
		get(this.path("/post"), 
				Resource.ContentType.APPLICATION_JSON, 
				( request, response ) -> {
			return postService.findAll();
		} , new JsonTransformer());

		// retrieve some data with parameters
		get(this.path("/post/:id"), 
				Resource.ContentType.APPLICATION_JSON, 
				( request, response ) -> {
			String uid = request.params(":id");
			Post post = postService.findById(uid);
			if (post != null) {
				response.status(200);
				return post;
			} else {
				response.status(404);
				return "";
			}
		} , new JsonTransformer());

		// Create some data
		post(this.path("/post"), 
				Resource.ContentType.APPLICATION_JSON, 
				( request, response ) -> {
			Gson gson = new Gson();
			Post post = gson.fromJson(request.body(), Post.class);
			postService.create(post);
			gson = null;
			response.status(201);
			return post;
		} , new JsonTransformer());

		// Update some data
		put(this.path("/post/:id"), 
				Resource.ContentType.APPLICATION_JSON, 
				( request, response ) -> {
			String uid = request.params(":id");

			Gson gson = new Gson();
			Post post = (Post) gson.fromJson(request.body(), Post.class);
			post = postService.update(uid, post);
			if (post != null) {
				response.status(200);
				return post;
			} else {
				response.status(404);
				return "";
			}
		});

		// publish post
		put(this.path("/post/:id/publish"), 
				Resource.ContentType.APPLICATION_JSON, 
				( request, response ) -> {
			String uid = request.params(":id");
			Post post = postService.publish(uid);
			if (post != null) {
				response.status(200);
				return post;
			} else {
				response.status(404);
				return null;
			}
		} , new JsonTransformer());

		// delete some data
		delete(this.path("/post/:id"), 
				Resource.ContentType.APPLICATION_JSON, 
				( request, response ) -> {
			String uid = request.params(":id");
			Post post = postService.deletePost(uid);
			if (post != null) {
				response.status(204);
				return "";
			} else {
				response.status(404);
				return "unknown resource with id=" + uid;
			}
		} , new JsonTransformer());

	}

}
