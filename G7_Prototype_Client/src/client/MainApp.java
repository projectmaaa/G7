package client;

import java.io.IOException;

import controllers.LoginWindowController;

public class MainApp {

	public static Client client;

	public static Client getClient() {
		return client;
	}

	public static void setClient(Client client) {
		MainApp.client = client;
	}

	public static void main(String[] args) throws IOException {
		MainApp.client = new Client("localhost",Client.DEFAULT_PORT);
		LoginWindowController.go();

	}

}
