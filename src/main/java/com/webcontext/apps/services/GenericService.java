package com.webcontext.apps.services;

import static spark.Spark.before;
import static spark.Spark.halt;

import com.webcontext.apps.utils.Configuration;

/**
 * Generic Service encapsulating service access authorization and some basic
 * rule for path access.
 * 
 * @author Frédéric Delorme
 *
 */
public class GenericService {

	/**
	 * Secret key to access web service.
	 */
	protected static String wsSecretKey = "MySecretkey";

	/**
	 * API basic path.
	 */
	protected String serviceApi = "api/";
	/**
	 * API basic version.
	 */
	protected String serviceVersion = "v0";

	/**
	 * Default constructor.
	 */
	public GenericService() {
		wsSecretKey = Configuration.get("wskey", "MySecretKey");
		serviceApi = Configuration.get("baseapi", "api/");
	}

	/**
	 * Initialize this API with a specific version.
	 * 
	 * @param version
	 */
	public GenericService(String version) {
		this.serviceVersion = version;
	}

	/**
	 * return base for all Rest service URL.
	 * 
	 * @return
	 */
	public String baseServicePath() {

		return serviceApi + this.serviceVersion;
	}

	public void init() {
		before("/*",
				(request, response) -> {
					boolean authenticated = ((request.headers("WS-KEY") != null) && request
							.headers("WS-KEY").equals(wsSecretKey));
					if (!authenticated) {
						halt(401, "You are not authorized here");
					}
				});
	}
}
