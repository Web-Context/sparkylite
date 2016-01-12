/**
 * 
 */
package com.webcontext.apps.sparky.tests;

import com.webcontext.apps.Post;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Functinal testing of PostResource REST service.
 * 
 * @author Frédéric Delorme
 *
 */
public class PostResourceTests {
	Post post;
	String baseURl="http://localhost:4567/api/v1/post";

    @When("I insert a Post with title $title and content $content and author $author and tags $tags")
    public void aPost(String title, String content, String author, String tags) {
    	
    }

    @Then("I have $nbPost Posts")
    public void iHaveNbPosts(long nbPost){
    	
    }

    @Then("the Post has title $title")
    public void thePostHasTitle(String title){
    	
    }
    
    @Then("the Post has author $author")
    public void thePostHasAuthor(String author){
    	
    }
    @Then("the Post has content $content")
    public void thePostHasContent(String content){
    	
    }
    @Then("the Post has tags $tags")
    public void thePostHasTags(String tags){
    	
    }

    
    /*------   ------*/
    
    /*
    @When("I edit Post $id setting title=$title")
    public void iEditAPostWithIdSettingsTitle(Long id, String title) {
    }
 
    @When("I edit Post 999 setting content=$content")
    public void iEditAPostWithIdSettingsContent(Long id, String content) {
    }
    
    @When("I delete Post $id")
    public void iDeleteAPostWithId(String Id){
    	
    }
    
    @Given("that on the DB there is a Post with id=$id title=$title content=$content author=$author")
    public void thatOnDBThereIsAPost(String id, String title, String content,String author){
    	
    }
    */
    
    
}
