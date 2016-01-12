Feature: Post management

    I can create Posts
    I can edit Posts
    I can delete Posts
  
    Scenario: Add a Post
        When I insert a Post with title "Foo" and content "foobar" and author "McG" and tags "test1,test2"
        Then I have 1 Posts
        Then the Post has title "Foo"
        Then the Post has author "McG"
        Then the Post has content "foobar"
        Then the Post has tags "test1,test2"

    Scenario: Edit the title
        Given that on the DB there is a Post with id=99 title="Bad title" content="foo bar zum!" author="McG"
        When I edit Post 99 setting title="Good Post"
        Then the Post 99 has title "Good Post"
        Then the Post 99 has content "foo bar zum!"

    Scenario: Edit the content
        Given that on the DB there is a Post with id=999 title="Good title" content="foo bar zum!" author="McG"
        When I edit Post 999 setting content="Foo bar zum! Zum zum!"
        Then the Post 999 has title "Good title"
        Then the Post 999 has content "Foo bar zum! Zum zum!"        

    Scenario: Delete a Post
        Given that on the DB there is a Post with UUID=9999 title="Bad title" header="foobarheader" content="foo bar zum!"
        When I delete Post 9999
		Then Post 9999 is not found