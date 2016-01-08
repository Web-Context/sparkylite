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
	     {"id":1,"title":"title 1","content":"content 1","tags":"tag1,tag2,tag3","author":"McG","published":false},
	     {"id":1,"title":"title 2","content":"content 2","tags":"tag1,tag2,tag3","author":"McG","published":false},
	     {"id":1,"title":"title 3","content":"content 3","tags":"tag1,tag2,tag3","author":"McG","published":false}
	    ]

or try a 

GET [http://localhost:4567/post/3](http://localhost:4567/post/3 "click to discover the rule N°3 of Rest Magic")

    Headers:
      Date: Fri, 08 Jan 2016 15:39:11 GMT
      Server: Jetty(9.3.2.v20150730)
      Transfer-Encoding: chunked
      Content-Type: text/html;charset=utf-8
    Data:
		{"id":3,"title":"title 3","content":"content 3","tags":"tag1,tag2,tag3","author":"McG","published":false}

Try to publish one on the existing posts :

PUT [http://localhost:4567/post/4/publish](http://localhost:4567/post/4/publish "click to activate the Rest Magic")
