package com.webcontext.apps;

/**
 * Simple post object to serve a blog.
 * 
 * @author Frédéric Delorme.
 *
 */
public class Post {

	private long id;
	private String title;
	private String content;
	private String tags;
	private String author;
	private boolean published = false;

	public Post(long id, String title, String content, String tags,
			String author) {
		this.id = id;
		this.content = content;
		this.title = title;
		this.tags = tags;
		this.author = author;
	}


	public Post(long id, String title, String content, String tags,
			String author, boolean published) {
		this(id,title,content,tags,author);
		this.published = published;
	}

	
	/*---- Getters/Setters ----*/

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the published
	 */
	public boolean isPublished() {
		return published;
	}

	/**
	 * @param published
	 *            the published to set
	 */
	public void setPublished(boolean published) {
		this.published = published;
	}

	/**
	 * Build String from object.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb
				.append("Post[")
				.append("id=")
				.append(id)
				.append(",title=")
				.append(title)
				// if content size >20 characters, truncate string to 20 char.
				.append(",content=")
				.append(content.length() > 20 ? content.substring(0, 20) : content)
				.append(",tags=").append(tags).append(",author=")
				.append(author).append("]").toString();
	}

}