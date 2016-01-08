package com.webcontext.apps;

import static spark.Spark.*;

import java.util.List;
import java.util.ArrayList;
import com.webcontext.apps.Post;

public class App {
 
 private static final String WS_SECRET_KEY="MySecretkey";

 private List<Post> posts = new ArrayList<Post>();

 public App(){
  posts.add(new Post(1,"title 1", "content 1", "tag1,tag2,tag3", "McG"));
  posts.add(new Post(1,"title 2", "content 2", "tag1,tag2,tag3", "McG"));
  posts.add(new Post(1,"title 3", "content 3", "tag1,tag2,tag3", "McG"));
  posts.add(new Post(1,"title 4", "content 4", "tag1,tag2,tag3", "McG"));
  posts.add(new Post(1,"title 5", "content 5", "tag1,tag2,tag3", "McG"));
 } 

 public static void main(String[] args) {
  // retrieve some data
  get("/post", (request, response) -> {
   return posts;
  }, new JsonTransformer());

  // retrieve some data with parameters
  get("/post/:id", (request, response) -> {
    for(POst p : posts){
      if ((""+p.getId()).equals(request.params(":id")){
        return "Post id: " + request.params(":id");
      }else{
        halt(404, "Post not found");
      }
    }
  });

  // Create some data
  post("/post", (request, response) -> {
   // ....
  });

  // Update some data
  put("/post/:id", (request, response) -> {
   String postId = request.params(":id");
   // ....
  });

  // delete some data
  delete("/", (request, response) -> {
   // ....
  });


  // Verify some thing in header on each request
  before((request, response) -> {
    boolean authenticated = (
      ( request.getHeader("WS-KEY")!=null) 
      && request.getHeader("WS-KEY").equals(WS_SECRET_KEY);
    if (!authenticated) {
      halt(401, "You are not welcome here");
    }
  });  
 } 
 
}