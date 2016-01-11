package com.webcontext.apps;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/**
 * Rest Service to manage Post CRUD operations.
 * 
 * @author Frédéric Delorme
 *
 */
public class PostService extends GenericService {

	private static List<Post> posts = new ArrayList<Post>();

	static {
		posts.add(new Post(1, "title 1", "content 1", "tag1,tag2,tag3", "McG",
				true));
		posts.add(new Post(2, "title 2", "content 2", "tag1,tag2,tag3", "McG",
				true));
		posts.add(new Post(3, "title 3", "content 3", "tag1,tag2,tag3", "McG",
				true));
		posts.add(new Post(4, "title 4", "content 4", "tag1,tag2,tag3", "McG"));
		posts.add(new Post(5, "title 5", "content 5", "tag1,tag2,tag3", "McG"));
	}

	/**
	 * Define version for this API.
	 */
	public PostService() {
		super("v1");
	}

	public void init() {
		// retrieve some data
		get(this.baseServicePath() + "/post", "application/json", (request,
				response) -> {
			List<Post> postsReturned = new ArrayList<Post>();
			for (Post p : posts) {
				if (p.isPublished()) {
					postsReturned.add(p);
				}
			}
			return postsReturned;
		}, new JsonTransformer());

		// retrieve some data with parameters
		get(this.baseServicePath() + "/post/:id",
				"application/json",
				(request, response) -> {
					for (Post p : posts) {
						if (("" + p.getId()).equals(request.params(":id"))
								&& p.isPublished()) {
							return p;
						}
					}
					response.status(404);
					return null;
				}, new JsonTransformer());

		// Create some data
		post(this.baseServicePath() + "/post", (request, response) -> {
			Gson gson = new Gson();
			Post post = gson.fromJson(request.body(), Post.class);
			posts.add(post);
			response.status(201);
			return post;
		}, new JsonTransformer());

		// Update some data
		put(this.baseServicePath() + "/post/:id", "application/json", (request,
				response) -> {
			for (Post p : posts) {
				if (("" + p.getId()).equals(request.params(":id"))) {
					posts.remove(p);
					Gson gson = new Gson();
					p.updateFrom((Post) gson.fromJson(request.body(),
							Post.class));

					posts.add(p);
					response.status(200);
					return p;
				}
			}
			response.status(404);
			return "";
		});

		// publish post
		put(this.baseServicePath() + "/post/:id/publish", "application/json", (
				request, response) -> {
			for (Post p : posts) {
				if (("" + p.getId()).equals(request.params(":id"))) {
					Post post = p;
					posts.remove(p);
					post.setPublished(true);
					posts.add(post);
					response.status(204);
					return post;
				}
			}
			response.status(404);
			return null;
		}, new JsonTransformer());

		// delete some data
		delete(this.baseServicePath() + "/post/:id", "application/json", (
				request, response) -> {
			for (Post p : posts) {
				if (("" + p.getId()).equals(request.params(":id"))) {
					posts.remove(p);
					response.status(200);
					return p;
				}
			}
			response.status(404);
			return "unknown resource with id=" + request.params(":id");
		});

	}
}
