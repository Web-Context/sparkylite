package com.webcontext.apps;

import static spark.Spark.before;
import static spark.Spark.halt;

/**
 * Generic Service encapsulating service access authorization and some basic
 * rule for path access.
 * 
 * @author Frédéric Delorme
 *
 */
public class GenericService {

	protected static String wsSecretKey = "MySecretkey";

	protected static final String SERVICE_API = "api/";
	protected String SERVICE_VERSION = "v0";

	/**
	 * Default constructor.
	 */
	public GenericService() {
		wsSecretKey = Configuration.get("wskey", "MySecretKey");
	}

	/**
	 * Initialize this API with a specific version.
	 * 
	 * @param version
	 */
	public GenericService(String version) {
		this.SERVICE_VERSION = version;
	}

	/**
	 * return base for all Rest service URL.
	 * 
	 * @return
	 */
	public String baseServicePath() {

		return SERVICE_API + this.SERVICE_VERSION;
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
