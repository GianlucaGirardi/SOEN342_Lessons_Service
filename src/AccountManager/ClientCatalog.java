package AccountManager;

import java.util.ArrayList;

public class ClientCatalog {
	private ArrayList<Client> clients;
	
	public ClientCatalog() {
		this.clients = new ArrayList<Client>();
	}
	
	public void displayAllClients() {
		this.clients.forEach(el -> System.out.println(el));
	}
	
}
