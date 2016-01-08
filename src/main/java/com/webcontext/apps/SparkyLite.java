package com.webcontext.apps;

import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class SparkyLite {

	private static final String WS_SECRET_KEY = "MySecretkey";

	private static List<Post> posts = new ArrayList<Post>();

	static {
		posts.add(new Post(1, "title 1", "content 1", "tag1,tag2,tag3", "McG",true));
		posts.add(new Post(2, "title 2", "content 2", "tag1,tag2,tag3", "McG",true));
		posts.add(new Post(3, "title 3", "content 3", "tag1,tag2,tag3", "McG",true));
		posts.add(new Post(4, "title 4", "content 4", "tag1,tag2,tag3", "McG"));
		posts.add(new Post(5, "title 5", "content 5", "tag1,tag2,tag3", "McG"));
	}

	public static void main(String[] args) {
		// retrieve some data
		get("/post", "application/json", (request, response) -> {
			List<Post> postsReturned = new ArrayList<Post>();
			for (Post p : posts) {
				if (p.isPublished()) {
					postsReturned.add(p);
				}
			}
			return postsReturned;
		}, new JsonTransformer());

		// retrieve some data with parameters
		get("/post/:id", "application/json", (request, response) -> {
			for (Post p : posts) {
				if (("" + p.getId()).equals(request.params(":id")) && p.isPublished()) {
					return p;
				}
			}
			response.status(404);
			return null;
		}, new JsonTransformer());

		// Create some data
		post("/post", (request, response) -> {
			Gson gson = new Gson();
			Post post = gson.fromJson(request.body(), Post.class);
			posts.add(post);
			response.status(200);
			return null;
		});

		// Update some data
		put("/post/:id", "application/json", (request, response) -> {
			for (Post p : posts) {
				if (("" + p.getId()).equals(request.params(":id"))) {
					posts.remove(p);
					Gson gson = new Gson();
					Post post = gson.fromJson(request.body(), Post.class);
					posts.add(post);
					response.status(200);
					return post;
				}
			}
			response.status(404);
			return null;
		});

		// Update some data
		put("/post/:id/publish", "application/json", (request, response) -> {
			for (Post p : posts) {
				if (("" + p.getId()).equals(request.params(":id"))) {
					Post post = p;
					posts.remove(p);
					post.setPublished(true);
					posts.add(post);
					response.status(200);
					return post;
				}
			}
			response.status(404);
			return null;
		}, new JsonTransformer());
		
		// delete some data
		delete("/post/:id", "application/json", (request, response) -> {
			for (Post p : posts) {
				if (("" + p.getId()).equals(request.params(":id"))) {
					response.status(200);
					return null;
				}
			}
			response.status(404);
			return null;
		});

		// Verify some thing in header on each request
		before("/*",
				(request, response) -> {
					boolean authenticated = ((request.headers("WS-KEY") != null) && request
							.headers("WS-KEY").equals(WS_SECRET_KEY));
					if (!authenticated) {
						response.status(401);
					}
				});
	}

}