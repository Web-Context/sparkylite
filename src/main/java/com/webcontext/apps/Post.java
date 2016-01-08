package	com.webcontext.apps;

public class Post {

	private long id;
	private String title;
	private String content;
	private String tags;
	private String author;
	private boolean published = false;

	public Post(long id, String title, String content, String tags, String author){
		this.id = id;
		this.content = content;
		this.title = title;
		this.tags = tags;
		this.author = author;
	}



	/*---- Getters/Setters ----*/
    // .....

	public boolean isPublished(){
		return this.published;
	}

	public void publish(){
		this.published = true;
	}

	/**
	 * Build String from object.
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		return sb.append("Post[")
		       .append("id=").append(id)
		       .append(",title=").append(title)
		       // if content size >20 characters, truncate string to 20 char.
		       .append(",content=").append(content.length>20 ? content.sustring(0,20) : content)
		       .append(",tags=").append(tags)
		       .append(",author=").append(author)
		       .append("]").toString();
	}

}