package urls;

public enum apiUrl {
	
	baseUrl("https://reqres.in/"),
	createpostapi("api/users"),
	retrievegetapi("api/users?page="),
	validateunknownapi("api/unknown");
	
	private String endpoint;
	apiUrl(String endpoint)
	{
		this.endpoint=endpoint;
		}
	
	public String endpoint() {
		// TODO Auto-generated method stub
		return endpoint;
	}


}
