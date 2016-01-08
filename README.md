# SparkyLite

A small rest web app to serve basic REST services, demonstrating SparkJava micro-service framewsork


Run the application and access it with a REst Client to 

GET [http://localhost:4567/post](http://localhost:4567/post "click to discover Rest Magic")

it will display all 5 post declared into the SparkyLite class:

    Headers:
      Date: Fri, 08 Jan 2016 15:35:23 GMT
      Server: Jetty(9.3.2.v20150730)
      Transfer-Encoding: chunked
      Content-Type: text/html;charset=utf-8
    Data:
	    [
	     {"id":1,"title":"title 1","content":"content 1","tags":"tag1,tag2,tag3","author":"McG","published":true},
	     {"id":1,"title":"title 2","content":"content 2","tags":"tag1,tag2,tag3","author":"McG","published":true},
	     {"id":1,"title":"title 3","content":"content 3","tags":"tag1,tag2,tag3","author":"McG","published":true}
	    ]

or try a 

GET [http://localhost:4567/post/3](http://localhost:4567/post/3 "click to discover the rule N°3 of Rest Magic")

    Headers:
      Date: Fri, 08 Jan 2016 15:39:11 GMT
      Server: Jetty(9.3.2.v20150730)
      Transfer-Encoding: chunked
      Content-Type: text/html;charset=utf-8
    Data:
		{"id":3,"title":"title 3","content":"content 3","tags":"tag1,tag2,tag3","author":"McG","published":true}


Accessing a not already published post (N°4):

GET [http://localhost:4567/post/4](http://localhost:4567/post/4 "click to access the unaccessible")
 
	Headers:
		404 Not Found
		Date:  Fri, 08 Jan 2016 16:23:32 GMT
		Content-Type:  text/html;charset=utf-8
		Transfer-Encoding:  chunked
		Server:  Jetty(9.3.2.v20150730)
	Data:	
		null


Ehehe .. 404 !  logic the 4th post is not already published !

Try to publish one on the existing posts :

PUT [http://localhost:4567/post/4/publish](http://localhost:4567/post/4/publish "click to activate the Rest Magic")

giving some smart result :

    Headers:
      Date: Fri, 08 Jan 2016 15:39:11 GMT
      Server: Jetty(9.3.2.v20150730)
      Transfer-Encoding: chunked
      Content-Type: text/html;charset=utf-8
    Data:
		{"id":4,"title":"title 4","content":"content 4","tags":"tag1,tag2,tag3","author":"McG","published":true}

We've just published the post N°4.

Now verify efficiency of this operation :

GET [http://localhost:4567/post](http://localhost:4567/post "click to discover unified 4th")

it will display all 4 post declared as published :

    Headers:
      Date: Fri, 08 Jan 2016 15:35:23 GMT
      Server: Jetty(9.3.2.v20150730)
      Transfer-Encoding: chunked
      Content-Type: text/html;charset=utf-8
    Data:
	    [
	     {"id":1,"title":"title 1","content":"content 1","tags":"tag1,tag2,tag3","author":"McG","published":true},
	     {"id":1,"title":"title 2","content":"content 2","tags":"tag1,tag2,tag3","author":"McG","published":true},
	     {"id":1,"title":"title 3","content":"content 3","tags":"tag1,tag2,tag3","author":"McG","published":true},
	     {"id":4,"title":"title 4","content":"content 4","tags":"tag1,tag2,tag3","author":"McG","published":true}
	    ]

That's it !

McG.