package com.webcontext.apps;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Post Service ready to serve CRUD operations on data source.
 * 
 * @author Frédéric Delorme
 *
 */
public class PostService {

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
	 * @param request
	 */
	public Post findById(String uid) {
		for (Post p : posts) {
			if (("" + p.getId()).equals(uid) && p.isPublished()) {
				return p;
			}
		}
		return null;
	}

	/**
	 * @return
	 */
	public List<Post> findAll() {
		List<Post> postsReturned = new ArrayList<Post>();
		for (Post p : posts) {
			if (p.isPublished()) {
				postsReturned.add(p);
			}
		}
		return postsReturned;
	}

	/**
	 * @param request
	 * @param response
	 */
	public Post deletePost(String uid) {
		for (Post p : posts) {
			if (("" + p.getId()).equals(uid)) {
				posts.remove(p);
				return p;
			}
		}
		return null;
	}

	/**
	 * @param uid
	 */
	public Post publish(String uid) {

		for (Post p : posts) {

			if (("" + p.getId()).equals(uid)) {
				Post post = p;
				posts.remove(p);
				post.setPublished(true);
				posts.add(post);
				return post;
			}
		}
		return null;
	}

	/**
	 * Update an existing Post with id=uid with some not null element from
	 * postData.
	 * 
	 * @param uid
	 * @param postData
	 * @return
	 */
	public Post update(String uid, Post postData) {
		for (Post p : posts) {
			if (("" + p.getId()).equals(uid)) {
				posts.remove(p);
				p.updateFrom(postData);

				posts.add(p);
				return p;
			}
		}
		return null;

	}

	public Post create(Post post) {
		posts.add(post);
		return post;
	}

}
