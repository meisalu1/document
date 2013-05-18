package documents.model.dao;

import documents.model.data.User;

public interface UserAuth {
	
	public User getUser(String name, String password);

}
