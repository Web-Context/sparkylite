package com.webcontext.apps.utils.service;

import java.io.IOException;

import javax.annotation.PostConstruct;

import com.webcontext.apps.utils.mongo.MongoJsonImport;
import com.webcontext.apps.utils.mongo.MongoRepository;

/**
 * BootStrap Application
 * @author Frederic
 *
 */
public class BootStrap {
	
	MongoRepository posts = new MongoRepository("games");
	
	
	public BootStrap(){
		if(!(posts.count()>0))
		{
			MongoJsonImport importData = new MongoJsonImport();
			try {
				importData.importJson("dataset/games.json", "games");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	@PostConstruct
	public void start(){
		
	}
	
	
}
