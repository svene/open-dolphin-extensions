package org.opendolphinx.extension.javafxclient;

import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.ClientModelStore;
import org.opendolphin.core.client.comm.ClientConnector;
import org.opendolphin.core.client.comm.HttpClientConnector;
import org.opendolphin.core.client.comm.UiThreadHandler;
import org.opendolphin.core.comm.JsonCodec;

public class ClientDolphinProvider {

	public static ClientDolphin newClientDolphin(String dolphinURL, UiThreadHandler uiThreadHandler) {
		ClientDolphin clientDolphin = new ClientDolphin();
		clientDolphin.setClientModelStore(new ClientModelStore(clientDolphin));

		ClientConnector connector = createConnector(clientDolphin, dolphinURL);
		connector.setUiThreadHandler(uiThreadHandler);
		clientDolphin.setClientConnector(connector);

		return clientDolphin;
	}

	private static ClientConnector createConnector(ClientDolphin clientDolphin, String dolphinURL) {
		//running real client server mode.
		HttpClientConnector connector = new HttpClientConnector(clientDolphin, dolphinURL);
		connector.setCodec(new JsonCodec());
		return connector;
	}

}
